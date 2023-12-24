package agenda.Alert;

import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class Alerta {
    public static void mostrarAlertaErro(String titulo, String cabecalho, String conteudo) {
        Image imageIcone = new Image(Objects.requireNonNull(Alerta.class.getResourceAsStream("/warning.png")));

        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        configurarAlerta(alertaError, titulo, cabecalho, conteudo);
        alertaError.setGraphic(new ImageView(imageIcone));

        alertaError.showAndWait();
    }

    public static void mostrarAlertaAtencao(String titulo, String cabecalho, String conteudo) {
        Image imageIcone = new Image(Objects.requireNonNull(Alerta.class.getResourceAsStream("/attention.png")));

        Alert alertaAtencao = new Alert(Alert.AlertType.WARNING);
        configurarAlerta(alertaAtencao, titulo, cabecalho, conteudo);
        alertaAtencao.setGraphic(new ImageView(imageIcone));

        alertaAtencao.showAndWait();
    }

    private static void configurarAlerta(Alert alerta, String titulo, String cabecalho, String conteudo) {
        Image imageIcone = new Image(Objects.requireNonNull(Alerta.class.getResourceAsStream("/contact.png")));
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(imageIcone);

        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
    }
}
