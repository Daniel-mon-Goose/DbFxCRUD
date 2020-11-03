package ru.nsu.protasov;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.postgresql.util.PSQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import ru.nsu.protasov.config.ApplicationContextProvider;
import ru.nsu.protasov.entity.Identifiable;
import ru.nsu.protasov.entity.Post;
import ru.nsu.protasov.repository.BasicRepositoryImpl;
import ru.nsu.protasov.service.CommonService;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SqlResultController {
    private PageRequest page;
    private List<Map<String, Object>> data;
    private ObservableList<ObservableList> observableData;
    private String request;
    private boolean isCRUD;
    private String datClass;
    private ApplicationContext ctx;

    @FXML
    private TableView sqlTable;

    @FXML
    private Button back;

    @FXML
    private Button forward;

    @FXML
    private Button addUpdateButton;

    @FXML
    private TableView editTable;

    @FXML
    void initialize() {
        ctx = ApplicationContextProvider.getContext();
    }

    void addUpdateInvisible() {
        addUpdateButton.setVisible(false);
        editTable.setVisible(false);
    }

    @FXML
    private void addUpdate() throws ClassNotFoundException, IOException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            List<String> values = (List<String>) editTable.getItems().get(0);
            List<String> keys = (List<String>) editTable.getColumns().stream().map((col) -> ((TableColumn) col).getText()).
                    collect(Collectors.toList());
            Map<String, String> crud = IntStream.range(0, keys.size()).boxed()
                    .collect(Collectors.toMap(keys::get, values::get));

            Class<?> createdClass = Class.forName("ru.nsu.protasov.entity." + datClass);
            Identifiable toAdd = (Identifiable) createdClass.getConstructor().newInstance();

            for (Map.Entry<String, String> entry: crud.entrySet()) {
                try {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Method m;
                    String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                    if (value.matches("-?\\d+")) {
                        m = createdClass.getMethod(methodName, int.class);
                        m.invoke(toAdd, Integer.parseInt(entry.getValue()));
                    } else if (value.isEmpty()) {

                    } else if (value.equals("true") || value.equals("false")) {
                        m = createdClass.getMethod(methodName, boolean.class);
                        m.invoke(toAdd, value.equals("true"));
                    } else if (key.equals("post")) {
                        m = createdClass.getMethod(methodName, Post.class);
                        m.invoke(toAdd, Post.valueOf(value));
                    } else {
                        m = createdClass.getMethod(methodName, String.class);
                        m.invoke(toAdd, value);
                    }
                } catch (Exception e) {
                    String key = entry.getKey();
                    String capKey = key.substring(0, 1).toUpperCase() + key.substring(1);
                    CommonService<Identifiable> service = (CommonService<Identifiable>) ctx.getBean(Class.
                            forName("ru.nsu.protasov.service." +
                            capKey + "ServiceImpl"));
                    Method m = createdClass.getMethod("set" + capKey,
                            Class.forName("ru.nsu.protasov.entity." + capKey));
                    Identifiable adding = service.findById(Integer.parseInt(entry.getValue()))
                            .orElseThrow(() -> new IllegalArgumentException(entry.getValue()));
                    m.invoke(toAdd, adding);
                }
            }
            if (toAdd.getId() == 0) {
                throw new RuntimeException("Wrong id");
            }
            CommonService<Identifiable> service = (CommonService<Identifiable>)ctx.
                    getBean(Class.forName("ru.nsu.protasov.service." + datClass + "ServiceImpl"));
            service.addOrEdit(toAdd);
        } catch (Exception e) {
            showInfo(e.getMessage());
            throw e;
        }
    }


    private static Scene produceErrorScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("error.fxml"));

        return new Scene(fxmlLoader.load());
    }

    private void setColumns(TableView table) throws ClassNotFoundException {
        table.getColumns().clear();
        List <String> keys;
        //List <Object> fields;
        Map<String, Object> obj;
        if (isCRUD) {
            Class<?> createdClass = Class.forName("ru.nsu.protasov.entity." + datClass);
            try {
                obj = App.objToMap((Identifiable)
                                createdClass.getConstructor().newInstance());
                //fields = new ArrayList<>(obj.values());postgres
                keys = new ArrayList<>(obj.keySet());
            } catch (Exception e) {
                return;
            }
        } else {
            if (!data.isEmpty()) {
                keys = new ArrayList<>(data.get(0).keySet());
            } else {
                return;
            }
        }

        for (int i = 0; i < keys.size(); ++i) {
            final int j = i;
            String key = keys.get(i);
            TableColumn col = new TableColumn(key);




            col.setCellFactory(TextFieldTableCell.forTableColumn());
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                    ObservableValue<String>>) param ->
                    new SimpleStringProperty(param.getValue().get(j).toString()));
            col.setEditable(true);
            col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                @Override
                public void handle(TableColumn.CellEditEvent t) {
                    ObservableList list = (ObservableList) t.getTableView().getItems().get(0);
                    list.set(t.getTablePosition().getColumn(), t.getNewValue());
                }
            });
            table.getColumns().addAll(col);

        }
    }

    private void fillEditTable() {
        ObservableList<String> row = FXCollections.observableArrayList();
        for (int i = 0; i < editTable.getColumns().size(); ++i) {
            row.add("");
        }
        ObservableList<Object> filler = FXCollections.observableArrayList();
        filler.add(row);
        editTable.setItems(filler);
        editTable.setEditable(true);
    }

    static void showInfo(String message) throws IOException {
        Scene errorScene = produceErrorScene();
        Label errorLabel = (Label) errorScene.lookup("#errorLabel");
        errorLabel.setText(message);
        Stage newWindow = new Stage();
        newWindow.setTitle("Error");
        newWindow.setScene(errorScene);
        newWindow.show();
    }


    private void populateSqlTable() {
        sqlTable.getItems().clear();
        observableData = FXCollections.observableArrayList();
        for (Map<String, Object> item: data) {
            ObservableList<String> row = FXCollections.observableArrayList();
            for (Map.Entry<String, Object> obj: item.entrySet()) {
                if (!isCRUD) {
                    row.add(obj.toString().split("=")[1]);
                } else {
                    if (!(obj.getValue() instanceof Integer || obj.getValue() instanceof String ||
                            obj.getValue() instanceof Boolean)) {
                        if (obj.getValue() != null) {
                            Integer id = (Integer) ((Map) obj.getValue()).get("id");
                            row.add(id.toString());
                        } else {
                            row.add("null");
                        }
                    } else {
                        row.add(obj.toString().split("=")[1]);
                    }
                }
            }
            observableData.add(row);
        }
        sqlTable.setItems(observableData);
    }

    void getData(String request, PageRequest page, List<Map<String, Object>> data, boolean isCRUD,
                 String datClass) throws ClassNotFoundException {
        this.data = data;
        this.page = page;
        this.request = request;
        this.isCRUD = isCRUD;
        this.datClass = datClass;
        setColumns(sqlTable);
        setColumns(editTable);
        populateSqlTable();
        fillEditTable();
    }

    @FXML
    private void turnPageForward() throws ClassNotFoundException {
        data.clear();
        ApplicationContext ctx = ApplicationContextProvider.getContext();
        if (!isCRUD) {
            BasicRepositoryImpl repo = ctx.getBean(BasicRepositoryImpl.class);
            data = repo.executeSQL(request, page.next());
        } else {
            Class<?> datClass = Class.forName("ru.nsu.protasov.service." +
                    request + "ServiceImpl");
            CommonService<Identifiable> CRUD = (CommonService<Identifiable>)
                    ctx.getBean(datClass);

            List<Identifiable> cruds = CRUD.getAll(page.next()).toList();
            for (Identifiable entity: cruds) {
                data.add(App.objToMap(entity));
            }
        }
        populateSqlTable();
    }

    @FXML
    private void turnPageBack() throws ClassNotFoundException {
        data.clear();
        BasicRepositoryImpl repo = ctx.getBean(BasicRepositoryImpl.class);
        if (!isCRUD) {
            data = repo.executeSQL(request, page.previous());
        } else {
            Class<?> datClass = Class.forName("ru.nsu.protasov.service." +
                    request + "ServiceImpl");
            CommonService<Identifiable> CRUD = (CommonService<Identifiable>)
                    ctx.getBean(datClass);

            List<Identifiable> cruds = CRUD.getAll(page.previous()).toList();
            for (Identifiable entity: cruds) {
                data.add(App.objToMap(entity));
            }
        }
        populateSqlTable();
    }
}
