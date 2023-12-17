package agenda.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBasePostgreSQL implements Database {

    @Override
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Agenda", "postgres", "Se7e@123");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void desconectar(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
