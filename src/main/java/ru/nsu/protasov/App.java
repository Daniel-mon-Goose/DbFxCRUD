package ru.nsu.protasov;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nsu.protasov.entity.Identifiable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    static <E extends Identifiable> Map<String, Object> objToMap(E obj) {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Building Company Database");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static Scene getScene() {
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}