package agenda.Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController  implements Initializable {

    @FXML
    public VBox vbFormMain;

    //TabPrincipal
    @FXML
    public TabPane tabPrincipal;
    @FXML
    public Tab tabCadastroContato;
    @FXML
    public Tab tabContatoSelecionar;
    @FXML
    public Tab tabAtualizarContato;
    @FXML
    public Tab tabExcluirContato;
    @FXML
    public AnchorPane achCadastro;
    @FXML
    public AnchorPane achSelecionar;
    @FXML
    public AnchorPane achAtualizar;
    @FXML
    public AnchorPane achExcluir;

    //Split da tela de Visualização do contato
    @FXML
    public SplitPane sptDadosContatos;
    @FXML
    public AnchorPane achDadosPrincipais;
    @FXML
    public TableView tblDadosPrincipais;
    @FXML
    public TableColumn colunaNome;
    @FXML
    public TableColumn colunaEmail;
    @FXML
    public AnchorPane achDadosCompletos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}