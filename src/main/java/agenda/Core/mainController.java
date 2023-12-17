package agenda.Core;

import java.sql.Connection;

import agenda.dao.ContatoDAO;
import agenda.database.Database;
import agenda.database.DatabaseFactory;
import agenda.model.Contato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class mainController  implements Initializable {
    @FXML
    public VBox vbFormMain;

    //TabsPrincipal
    @FXML
    private TabPane tabPrincipal;
    @FXML
    private Tab tabCadastroContato;
    @FXML
    private Tab tabContatoSelecionar;
    @FXML
    private Tab tabAtualizarContato;
    @FXML
    private Tab tabExcluirContato;


    // Cadastrar Contato
    @FXML
    private TextField edtNomeCompletoCadastro;
    @FXML
    private TextField edtDataDeNascimentoCadastro;
    @FXML
    private TextField edtNumeroDeTelefoneCadastro;
    @FXML
    private TextField edtEnderecoDeEmailCadastro;
    @FXML
    private TextField edtDescricaoCadastro;
    @FXML
    private Button btnSalvarCadastro;


    // Visualização do contato
    @FXML
    private TableView <Contato> tblSelecionar;
    @FXML
    private TableColumn <Contato, String> colunaNomeSelecionar;
    @FXML
    private TableColumn <Contato, String> colunaEmailSelecionar;
    @FXML
    private TextField edtNomeCompletoSelecionar;
    @FXML
    private TextField edtDataDeNascimentoSelecionar;
    @FXML
    private TextField edtNumeroDeTelefoneSelecionar;
    @FXML
    private TextField edtEnderecoDeEmailSelecionar;
    @FXML
    private TextField edtDescricaoSelecionar;
    @FXML
    private Button btnRecarregarSelecionar;


    // Atualização do contato
    @FXML
    private TableView <Contato> tblAtualizar;
    @FXML
    private TableColumn <Contato, String> colunaAtualizarNome;
    @FXML
    private TextField edtNomeCompletoAtualizar;
    @FXML
    private TextField edtDataDeNascimentoAtualizar;
    @FXML
    private TextField edtNumeroDeTelefoneAtualizar;
    @FXML
    private TextField edtEnderecoDeEmailAtualizar;
    @FXML
    private TextField edtDescricaoAtualizar;
    @FXML
    private Button btnAtualizar;


    // Excluir do contato
    @FXML
    private TableView <Contato> tblExcluir;
    @FXML
    private TableColumn <Contato, String> colunaExcluirNome;
    @FXML
    private TableColumn <Contato, String> colunaExcluirNumeroDeTelefone;
    @FXML
    private TableColumn <Contato, String> colunaExcluirEmail;
    @FXML
    private Button btnExcluir;


    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database != null ? database.conectar() : null;
    private  final ContatoDAO contatoDAO;

    {
        try {
            assert connection != null;
            contatoDAO = new ContatoDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarTableViewContatos();

        tblSelecionar.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTblSelecionar(newValue)
        );
        tblAtualizar.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTblAtualizar(newValue)
        );
    }

    public void carregarTableViewContatos(){
        colunaNomeSelecionar.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmailSelecionar.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaAtualizarNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaExcluirNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaExcluirEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaExcluirNumeroDeTelefone.setCellValueFactory(new PropertyValueFactory<>("Numero"));

        List<Contato> listaContatos = contatoDAO.listarContato();
        ObservableList<Contato> observableListContatos = FXCollections.observableArrayList(listaContatos);

        tblSelecionar.setItems(observableListContatos);
        tblAtualizar.setItems(observableListContatos);
        tblExcluir.setItems(observableListContatos);
    }

    public void selecionarItemTblSelecionar(Contato contato){
        if (contato != null) {
            edtNomeCompletoSelecionar.setText(contato.getNome());
            edtDataDeNascimentoSelecionar.setText(contato.getDataAniversario());
            edtNumeroDeTelefoneSelecionar.setText(String.valueOf(contato.getNumero()));
            edtEnderecoDeEmailSelecionar.setText(contato.getEmail());
            edtDescricaoSelecionar.setText(contato.getDescricao());
        } else {
            edtNomeCompletoSelecionar.setText("");
            edtDataDeNascimentoSelecionar.setText("");
            edtNumeroDeTelefoneSelecionar.setText("");
            edtEnderecoDeEmailSelecionar.setText("");
            edtDescricaoSelecionar.setText("");
        }
    }

    public void selecionarItemTblAtualizar(Contato contato){
        if (contato != null) {
            edtNomeCompletoAtualizar.setText(contato.getNome());
            edtDataDeNascimentoAtualizar.setText(contato.getDataAniversario());
            edtNumeroDeTelefoneAtualizar.setText(String.valueOf(contato.getNumero()));
            edtEnderecoDeEmailAtualizar.setText(contato.getEmail());
            edtDescricaoAtualizar.setText(contato.getDescricao());

//            adicionarMascaraTelefone(edtNumeroDeTelefoneAtualizar);
        } else {
            edtNomeCompletoAtualizar.setText("");
            edtDataDeNascimentoAtualizar.setText("");
            edtNumeroDeTelefoneAtualizar.setText("");
            edtEnderecoDeEmailAtualizar.setText("");
            edtDescricaoAtualizar.setText("");
        }
    }

    public void atualizarContato(){
        Contato contatoSelecionado = tblAtualizar.getSelectionModel().getSelectedItem();

        if (contatoSelecionado != null) {
            contatoSelecionado.setNome(edtNomeCompletoAtualizar.getText());
            contatoSelecionado.setNumero(Long.valueOf(edtNumeroDeTelefoneAtualizar.getText()));;
            contatoSelecionado.setDataAniversario((edtDataDeNascimentoAtualizar.getText()));
            contatoSelecionado.setEmail(edtEnderecoDeEmailAtualizar.getText());
            contatoSelecionado.setDescricao(edtDescricaoAtualizar.getText());

            if (contatoDAO.alterarContato(contatoSelecionado)) {
                edtNomeCompletoAtualizar.setText("");
                edtDataDeNascimentoAtualizar.setText("");
                edtNumeroDeTelefoneAtualizar.setText("");
                edtEnderecoDeEmailAtualizar.setText("");
                edtDescricaoAtualizar.setText("");

                carregarTableViewContatos();
            }
        }
    }

    public void excluirContato(){
        Contato contatoSelecionado = tblExcluir.getSelectionModel().getSelectedItem();

        if (contatoSelecionado != null) {
            if (contatoDAO.deletarContato(contatoSelecionado)) {
                carregarTableViewContatos();
            }
        }

    }

    public void incluirContato(){
        if (edtNomeCompletoCadastro.getText() != "" && edtDataDeNascimentoCadastro.getText() != "" && edtNumeroDeTelefoneCadastro.getText() != "" &&
            edtEnderecoDeEmailCadastro.getText() != "" && edtDescricaoCadastro.getText() != "") {

            Contato contatoNovo = new Contato();

            contatoNovo.setNome(edtNomeCompletoCadastro.getText());
            contatoNovo.setDataAniversario(edtDataDeNascimentoCadastro.getText());
            contatoNovo.setNumero(Long.valueOf(edtNumeroDeTelefoneCadastro.getText()));
            contatoNovo.setEmail(edtEnderecoDeEmailCadastro.getText());
            contatoNovo.setDescricao(edtDescricaoCadastro.getText());

            if (contatoDAO.inserirContato(contatoNovo)) {
                edtNomeCompletoCadastro.setText("");
                edtDataDeNascimentoCadastro.setText("");
                edtNumeroDeTelefoneCadastro.setText("");
                edtEnderecoDeEmailCadastro.setText("");
                edtDescricaoCadastro.setText("");

                carregarTableViewContatos();
            }
        }
    }

//    public static void adicionarMascaraTelefone(TextField textField) {
//        textField.setOnKeyTyped(event -> {
//            String textoAtual = textField.getText();
//            String caractereDigitado = event.getCharacter();
//
//            if (caractereDigitado.matches("[0-9]")) {
//                textoAtual += caractereDigitado;
//
//                if (textoAtual.length() == 1) {
//                    textoAtual = "(" + textoAtual;
//                } else if (textoAtual.length() == 3) {
//                    textoAtual += ") ";
//                } else if (textoAtual.length() == 9) {
//                    textoAtual += "-";
//                }
//
//                textField.setText(textoAtual);
//                event.consume();
//            } else {
//                event.consume();
//            }
//        });
//    }
}