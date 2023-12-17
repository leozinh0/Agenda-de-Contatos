package agenda.dao;

import agenda.model.Contato;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContatoDAO {
    private final Connection connection;

    public ContatoDAO(Connection connection) throws SQLException {
        this.connection = connection;

        if (!tabelaContatoExiste()) {
            criarTabelaContato();
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public boolean inserirContato(Contato contato) {
        String instrucaoSQL = "INSERT INTO Contato(Nome,            " +
                              "                    Numero,          " +
                              "                    DataAniversario, " +
                              "                    Email,           " +
                              "                    Descricao)       " +
                              "             VALUES(?,               " +
                              "                    ?,               " +
                              "                    ?,               " +
                              "                    ?,               " +
                              "                    ?)               ";
        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setString(1, contato.getNome());
            statement.setLong(2, contato.getNumero());
            statement.setDate(3, converterStringParaSqlDate(contato.getDataAniversario().replaceAll("/", "-")));
            statement.setString(4, contato.getEmail());
            statement.setString(5, contato.getDescricao());

            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterarContato(Contato contato) {
        String instrucaoSQL = "UPDATE Contato SET Nome = ?,            " +
                              "                   Numero = ?,          " +
                              "                   DataAniversario = ?, " +
                              "                   Email = ?,           " +
                              "                   Descricao = ?        " +
                              "             WHERE CodContato = ?       ";
        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setString(1, contato.getNome());
            statement.setLong(2, contato.getNumero());
            statement.setDate(3, converterStringParaSqlDate(contato.getDataAniversario().replaceAll("/", "-")));
            statement.setString(4, contato.getEmail());
            statement.setString(5, contato.getDescricao());
            statement.setInt(6, contato.getCodContato());

            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean deletarContato(Contato contato) {
        String instrucaoSQL = "DELETE FROM Contato        " +
                              "      WHERE CodContato = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setInt(1, contato.getCodContato());

            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public List<Contato> listarContato() {
        String instrucaoSQL = "SELECT to_char(DataAniversario, 'DD-MM-YYYY') AS DataAniversario, * FROM Contato";
        List<Contato> ListaContatos = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contato contato = new Contato();

                contato.setCodContato(resultSet.getInt("CodContato"));
                contato.setNome(resultSet.getString("Nome"));
                contato.setNumero(resultSet.getLong("Numero"));
                contato.setDataAniversario(resultSet.getString("DataAniversario").replaceAll("-", "/"));
                contato.setEmail(resultSet.getString("Email"));
                contato.setDescricao(resultSet.getString("Descricao"));

                ListaContatos.add(contato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListaContatos;
    }

    private boolean tabelaContatoExiste() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, connection.getSchema(), "contato", null);
            return tables.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void criarTabelaContato() {
        try {
            String instrucaoSQL = "CREATE TABLE Contato (                  								   " +
                                  "	   CodContato Serial,                  								   " +
                                  "	   Nome VARCHAR(50) NOT NULL,          								   " +
                                  "	   Numero BIGINT UNIQUE,             								   " +
                                  "	   DataAniversario DATE NOT NULL,      								   " +
                                  "	   Email VARCHAR(100) NOT NULL,     								   " +
                                  "    Descricao VARCHAR(1000) NOT NULL);  								   " +
                                  "                                        								   " +
                                  "ALTER TABLE Contato ADD CONSTRAINT PK_Contato PRIMARY KEY (CodContato); ";
            connection.createStatement().executeUpdate(instrucaoSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Date converterStringParaSqlDate(String stringData) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date utilDate = formato.parse(stringData);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
