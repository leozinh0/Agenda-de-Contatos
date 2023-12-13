package agenda.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBasePostgreSQL implements Database{
    private Connection connection;
    @Override
    public Connection conectar() throws SQLException{
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Agenda", "postgres", "Se7e@123");
            return this.connection;
        } catch (SQLException e) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    @Override
    public void desconectar(Connection conexao) {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(DataBasePostgreSQL.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
