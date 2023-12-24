package agenda.database;

public class DatabaseFactory {

    public static Database getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new DataBasePostgreSQL();
        }
        return null;
    }
}
