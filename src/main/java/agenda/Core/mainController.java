package agenda.Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController  implements Initializable {
    @FXML
    public VBox vbFormMain;

    //TabsPrincipal
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


    // Cadastrar Contato
    @FXML
    public TextField edtNomeCompletoCadastro;
    @FXML
    public TextField edtDataDeNascimentoCadastro;
    @FXML
    public TextField edtNumeroDeTelefoneCadastro;
    @FXML
    public TextField edtEnderecoDeEmailCadastro;
    @FXML
    public TextField edtDescricaoCadastro;
    @FXML
    public Button btnSalvarCadastro;


    // Visualização do contato
    @FXML
    public TableView tblDadosPrincipais;
    @FXML
    public TableColumn colunaNomeSelecionar;
    @FXML
    public TableColumn colunaEmailSelecionar;
    @FXML
    public TextField edtNomeCompletoSelecionar;
    @FXML
    public TextField edtDataDeNascimentoSelecionar;
    @FXML
    public TextField edtNumeroDeTelefoneSelecionar;
    @FXML
    public TextField edtEnderecoDeEmailSelecionar;
    @FXML
    public TextField edtDescricaoSelecionar;
    @FXML
    public Button btnRecarregarSelecionar;


    // Atualização do contato
    @FXML
    public TableView tblAtualizar;
    @FXML
    public TableColumn colunaAtualizarNome;
    @FXML
    public TextField edtNomeCompletoAtualizar;
    @FXML
    public TextField edtDataDeNascimentoAtualizar;
    @FXML
    public TextField edtNumeroDeTelefoneAtualizar;
    @FXML
    public TextField edtEnderecoDeEmailAtualizar;
    @FXML
    public TextField edtDescricaoAtualizar;
    @FXML
    public Button btnAtualizar;


    // Excluir do contato
    @FXML
    public TableView tblExcluir;
    @FXML
    public TableColumn colunaExcluirNome;
    @FXML
    public TableColumn colunaExcluirNumeroDeTelefone;
    @FXML
    public TableColumn colunaExcluirEmail;
    @FXML
    public Button btnExcluir;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}