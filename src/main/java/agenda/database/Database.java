package agenda.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
    public Connection conectar() throws SQLException;
    public void desconectar(Connection conexao);
}
