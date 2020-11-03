package ru.nsu.protasov;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.nsu.protasov.config.DataConfig;
import ru.nsu.protasov.config.UserParams;


public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void switchToMainMenu() throws IOException, BeanCreationException {
        String login = loginField.getText(), password = passwordField.getText();
        UserParams.setLogin(login);
        UserParams.setPassword(password);
        AnnotationConfigApplicationContext ctx;

        try {
            ctx = new AnnotationConfigApplicationContext(DataConfig.class);
        } catch (BeanCreationException e) {
            errorLabel.setOpacity(1);
            throw e;
        }
        App.setRoot("mainmenu");
    }
}
