package agenda.dao;

import agenda.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContatoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public boolean inserirContato(Contato contato) {
        String instrucaoSQL = "INSERT INTO Contato(CodContato,     " + System.lineSeparator() +
                              "                    Nome,           " + System.lineSeparator() +
                              "                    Sobrenome       " + System.lineSeparator() +
                              "                    Numero          " + System.lineSeparator() +
                              "                    DataAniversario " + System.lineSeparator() +
                              "                    Email           " + System.lineSeparator() +
                              "                    Descricao)      " + System.lineSeparator() +
                              "             VALUES(?,              " + System.lineSeparator() +
                              "                    ?,              " + System.lineSeparator() +
                              "                    ?,              " + System.lineSeparator() +
                              "                    ?,              " + System.lineSeparator() +
                              "                    ?,              " + System.lineSeparator() +
                              "                    ?,              " + System.lineSeparator() +
                              "                    ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setInt(1, contato.getCodContato());
            statement.setString(2, contato.getNome());
            statement.setString(3, contato.getSobrenome());
            statement.setString(4, contato.getNumero());
            statement.setString(5, contato.getDataAniversario());
            statement.setString(6, contato.getEmail());
            statement.setString(7, contato.getDescricao());

            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean alterarContato(Contato contato) {
        String instrucaoSQL = "UPDATE Contato SET Nome = ?,            " + System.lineSeparator() +
                              "                   Sobrenome = ?,       " + System.lineSeparator() +
                              "                   Numero = ?,          " + System.lineSeparator() +
                              "                   DataAniversario = ?, " + System.lineSeparator() +
                              "                   Email = ?,           " + System.lineSeparator() +
                              "                   Descricao = ?        " + System.lineSeparator() +
                              "             WHERE CodContato = ?       ";
        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getSobrenome());
            statement.setString(3, contato.getNumero());
            statement.setString(4, contato.getDataAniversario());
            statement.setString(5, contato.getEmail());
            statement.setString(6, contato.getDescricao());
            statement.setInt(7, contato.getCodContato());

            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean deletarContato(Contato contato) {
        String instrucaoSQL = "DELETE FROM Contato        " + System.lineSeparator() +
                              "      WHERE CodContato = ? " ;

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
        String instrucaoSQL = "SELECT * FROM Contato";

        List<Contato> result = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(instrucaoSQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contato contato = new Contato();

                contato.setCodContato(resultSet.getInt("CodContato"));
                contato.setNome(resultSet.getString("Nome"));
                contato.setSobrenome(resultSet.getString("Sobrenome"));
                contato.setNumero(resultSet.getString("Numero"));
                contato.setDataAniversario(resultSet.getString("DataAniversario"));
                contato.setEmail(resultSet.getString("Email"));
                contato.setDescricao(resultSet.getString("Descricao"));

                result.add(contato);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
