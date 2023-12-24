package agenda.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static agenda.Alert.Alerta.mostrarAlertaErro;

public class DataBasePostgreSQL implements Database {

    @Override
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Agenda", "postgres", "Se7e@123");
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, e);
            mostrarAlertaErro("Error ", "Ocorreu uma falha ao estabelecer uma conexão ao banco de dados.", e.toString());
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, e);
            mostrarAlertaErro("Error ", "Ocorreu uma falha ao desconectar do banco de dados.", e.toString());
        }
    }
}
