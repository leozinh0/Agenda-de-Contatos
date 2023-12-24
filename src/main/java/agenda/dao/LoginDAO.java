package agenda.dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static agenda.Alert.Alerta.mostrarAlertaErro;

public class LoginDAO {
    private final Connection connection;

    public LoginDAO(Connection connection) throws SQLException {
        this.connection = connection;

        if (!tabelaUsuarioExiste()) {
            criarTabelaUsuario();
        }
    }

    private boolean tabelaUsuarioExiste() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, connection.getSchema(), "usuario", null);
            return tables.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void criarTabelaUsuario() {
        try {
            String instrucaoSQL = "CREATE TABLE Usuario (                  								   " +
                                  "	   CodUsuario Serial,                  								   " +
                                  "	   NomeUsuario VARCHAR(100) UNIQUE,          					       " +
                                  "    Senha VARCHAR(100) NOT NULL);  								       " +
                                  "                                        								   " +
                                  "ALTER TABLE Usuario ADD CONSTRAINT PK_Usuario PRIMARY KEY (CodUsuario); " +
                                  "                                        								   " +
                                  "INSERT INTO usuario (nomeusuario,      								   " +
                                  "                     senha)            								   " +
                                  "     VALUES ('Admin', '123');           								   " ;

            connection.createStatement().executeUpdate(instrucaoSQL);
        } catch (SQLException e) {
            mostrarAlertaErro("Error ", "Ocorreu uma falha durante a tentativa de criar a tabela 'Usuario' no banco de dados.", e.toString());
        }
    }

    public Boolean UsuarioValido(String Usuario, String Senha){
        String instrucaoSQL = "SELECT *               " +
                "                FROM Usuario         " +
                "               WHERE NomeUsuario = ? " +
                "                 AND Senha = ?       " ;

        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setString(1, Usuario);
            statement.setString(2, Senha);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            mostrarAlertaErro("Error ", "Ocorreu uma falha ao executar a instrução de seleção no banco de dados.", e.toString());
            return false;
        }
    }
}
