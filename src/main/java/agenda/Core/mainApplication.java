package agenda.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlFormMain = new FXMLLoader(mainApplication.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlFormMain.load());

        stage.setResizable(false);
        stage.setTitle("Agenda de Contatos FX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}