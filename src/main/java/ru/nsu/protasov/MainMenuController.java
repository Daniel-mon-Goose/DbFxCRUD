package ru.nsu.protasov;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import ru.nsu.protasov.config.ApplicationContextProvider;
import ru.nsu.protasov.config.UserParams;
import ru.nsu.protasov.entity.Identifiable;
import ru.nsu.protasov.repository.BasicRepositoryImpl;
import ru.nsu.protasov.service.CommonService;
import ru.nsu.protasov.service.CustomerServiceImpl;
import ru.nsu.protasov.service.TechnicalSpecialistServiceImpl;

public class MainMenuController {
    private final static int PAGESIZE = 5;
    @FXML
    private TextArea requestField;

    private Map<String, String> filterLabels = new HashMap<>();

    @FXML
    private TextArea errorsField;

    @FXML
    private ComboBox tableChoice;

    @FXML
    private TextField filterData;

    @FXML
    private Label filterSign;

    @FXML
    private PasswordField dbPassword;

    @FXML
    private PasswordField dbConfirmPassword;

    @FXML
    private TextField dbUser;

    @FXML
    private TextField dbKeyword;

    private String user;
    private String keyword;
    private ApplicationContext ctx;

    private Scene produceScene(String request, List<Map<String, Object>> data,
                               PageRequest page, boolean isCRUD) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("sqlresult.fxml"));
        Scene result = new Scene(fxmlLoader.load());
        SqlResultController controller = fxmlLoader.getController();
        controller.getData(request, page, data, isCRUD,
                (String) tableChoice.getValue());
        if (!isCRUD) {
            controller.addUpdateInvisible();
        }

        return result;
    }

    @FXML
    private void findUser() throws IOException {
        user = dbUser.getText();
        keyword = dbKeyword.getText();

        if (!dbPassword.getText().equals(dbConfirmPassword.getText())) {
            SqlResultController.showInfo("Password and confirmation do not match");
            return;
        }

        BasicRepositoryImpl repo = ctx.getBean(BasicRepositoryImpl.class);
        PageRequest page = PageRequest.of(0, PAGESIZE);
        List<Map<String, Object>> data = repo.executeSQL("SELECT * FROM users WHERE login = '"
                + user + "'", page);
        long count = (long) repo.executeSQL("SELECT count(*) FROM users", page).get(0).get("count");

        if (!data.isEmpty()) {
            if (!Base64.getUrlEncoder().encodeToString(keyword.getBytes(StandardCharsets.UTF_8))
                    .equals(data.get(0).get("keyword"))) {
                SqlResultController.showInfo("Wrong keyword");
                return;
            }

            repo.execute(String.format("ALTER USER %s WITH ENCRYPTED PASSWORD '%s'", user, dbPassword.getText()));
        } else {
            keyword = Base64.getUrlEncoder().encodeToString(keyword.getBytes(StandardCharsets.UTF_8));

            String request = String.format("INSERT INTO users(id, login, keyword) " +
                                    "VALUES(%d, '%s', '%s');\n" +
                                    "CREATE USER %s WITH ENCRYPTED PASSWORD '%s';\n" +
                                    "GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO %s;",
                            count, user, keyword, user, dbPassword.getText(), user);


            repo.execute(request);
        }
    }

    @FXML
    private void changeFilter() {
        filterData.setText("");
        String ent = (String) tableChoice.getValue();
        String sign = filterLabels.get(ent);
        if (filterLabels.get(ent) != null) {
            filterData.setEditable(true);
            filterSign.setText(sign);
        } else {
            filterData.setEditable(false);
            filterSign.setText("Filter not available");
        }
    }

    @FXML
    public void initialize() {
        ctx = ApplicationContextProvider.getContext();
        errorsField.setStyle("-fx-text-fill: #8b0000");
        filterLabels.put("Customer", "findByName");
        filterLabels.put("TechnicalSpecialist", "findMoreExperienceYears");

        tableChoice.getItems().addAll(
                "Area",
                "Bridge",
                "Brigade",
                "BrigadeObjectWork",
                "BuildingObject",
                "ConstructionManagement",
                "Customer",
                "Foreman",
                "Locksmith",
                "Machinery",
                "Mason",
                "Master",
                "Material",
                "Outlay",
                "OutlayExceedance",
                "ResidentialHouse",
                "ScheduleDelay",
                "TechnicalSpecialist",
                "Worker",
                "WorkSchedule",
                "WorkType"
        );
    }

    @FXML
    private void showTable() throws ClassNotFoundException, IOException {
        String table = (String) tableChoice.getValue();
        Class<?> datClass = Class.forName("ru.nsu.protasov.service." +
                table + "ServiceImpl");

        PageRequest page = PageRequest.of(0, PAGESIZE);
        List<? extends Identifiable> cruds;

        if (filterData.isEditable() && !filterData.getText().isEmpty()) {
            if (table.equals("Customer")) {
                CustomerServiceImpl CRUD = (CustomerServiceImpl) ctx.getBean(datClass);
                cruds = CRUD.findByName(filterData.getText(), page).toList();
            } else if (table.equals("TechnicalSpecialist")) {
                TechnicalSpecialistServiceImpl CRUD = (TechnicalSpecialistServiceImpl) ctx.getBean(datClass);
                try {
                    cruds = CRUD.findMoreExpYears(filterData.getText(), page).toList();
                } catch (NumberFormatException e) {
                    SqlResultController.showInfo("Provide a number");
                    return;
                }
            } else {
                throw new RuntimeException();
            }
        } else {
            CommonService<Identifiable> CRUD = (CommonService<Identifiable>)
                    ctx.getBean(datClass);
            cruds = CRUD.getAll(page).toList();
        }

        List<Map<String, Object>> data = new ArrayList<>();
        for (var entity: cruds) {
            data.add(App.objToMap(entity));
        }

        Scene sqlScene = produceScene(table, data, page, true);
        Stage newWindow = new Stage();
        newWindow.setTitle("Request Result");
        newWindow.setScene(sqlScene);
        newWindow.show();
    }


    @FXML
    private void showRawRequestResult() throws IOException, SQLException {
        String request = requestField.getText();
        try {
            BasicRepositoryImpl repo = ctx.getBean(BasicRepositoryImpl.class);
            PageRequest page = PageRequest.of(0, PAGESIZE);
            List<Map<String, Object>> data;

            if (!request.toLowerCase().startsWith("select")) {
                repo.execute(request);
                SqlResultController.showInfo("Processed");
            } else {
                data = repo.executeSQL(request, page);
                Scene sqlScene = produceScene(request, data, page, false);
                Stage newWindow = new Stage();
                newWindow.setTitle("Request Result");
                newWindow.setScene(sqlScene);
                newWindow.show();
            }
        } catch (DataAccessException | ClassNotFoundException e) {
            errorsField.setText(e.getMessage());
        }
    }
}