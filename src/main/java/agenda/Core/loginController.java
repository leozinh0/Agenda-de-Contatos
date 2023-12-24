package agenda.Core;

import agenda.dao.LoginDAO;
import agenda.database.Database;
import agenda.database.DatabaseFactory;
import static agenda.Alert.Alerta.mostrarAlertaAtencao;
import static agenda.Alert.Alerta.mostrarAlertaErro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    public TextField edtUsuario;
    @FXML
    public PasswordField edtSenha;
    @FXML
    public Button btnLogin;
    private LoginDAO loginDAO = null;

    public loginController() {
        try {
            Database database = DatabaseFactory.getDatabase("postgresql");
            Connection connection = database != null ? database.conectar() : null;
            loginDAO = new LoginDAO(connection);
        } catch (SQLException e) {
            mostrarAlertaErro("Error ", "Houve uma falha na tentativa de conexão com o banco de dados.", e.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(actionEvent -> carregarUsuario());
    }

    public void carregarUsuario(){
        if (edtUsuario.getText().isEmpty()) {
            if (!"Admin".equals(edtUsuario.getPromptText())) {
                mostrarAlertaAtencao("Atenção ", "Usuário não especificado.", "");
                return;
            }
        } else if (edtSenha.getText().isEmpty()) {
            mostrarAlertaAtencao("Atenção ", "Senha não especificada.", "");
            return;
        }

        String usuario = (edtUsuario.getPromptText().equals("Admin")) ? edtUsuario.getPromptText() : edtUsuario.getText();
        Boolean usuarioValido = loginDAO.UsuarioValido(usuario, edtSenha.getText());

        if (usuarioValido) {
            Stage stageLogin = (Stage) btnLogin.getScene().getWindow();
            stageLogin.close();

            try {
                Stage stagePrincipal = new Stage();
                FXMLLoader fxmlLoaderLogin = new FXMLLoader(getClass().getResource("telaPrincipal.fxml"));
                Image imageIcone = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/contact.png")));

                Scene scene = new Scene(fxmlLoaderLogin.load());
                stagePrincipal.setTitle("Agenda de Contatos FX");
                stagePrincipal.getIcons().add(imageIcone);
                stagePrincipal.setScene(scene);
                stagePrincipal.show();
            } catch (IOException e) {
                mostrarAlertaErro("Error ", "Ocorreu um erro durante a criação da tela principal da agenda.", e.toString());
            }
        } else {
            mostrarAlertaAtencao("Atenção ", "As credenciais de usuário ou senha fornecidas são inválidas.", "");
        }
    }
}
