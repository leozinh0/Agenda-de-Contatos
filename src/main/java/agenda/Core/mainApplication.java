package agenda.Core;

import static agenda.Alert.Alerta.mostrarAlertaErro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlFormLogin = new FXMLLoader(mainApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlFormLogin.load());
            Image imageIcone = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/contact.png")));

            stage.setResizable(false);
            stage.getIcons().add(imageIcone);
            stage.setTitle("Agenda de Contatos FX");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            mostrarAlertaErro("Error ", "Ocorreu um erro durante a inicialização da agenda.", e.toString());;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}