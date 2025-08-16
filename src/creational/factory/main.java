package creational.factory;
// https://www.digitalocean.com/community/tutorials/java-design-patterns-example-tutorial
interface DatabaseConnection {
    void connect();
}

class MySQLConnection implements DatabaseConnection {
    @Override
    public void connect(){
        System.out.println("Connecting to MySQL Database...");
    }
}

class OracleConnection implements DatabaseConnection {
    @Override
    public void connect(){
        System.out.println("Connecting to Oracle Database...");
    }
}

class DatabaseConnectionFactory {
    public static DatabaseConnection createConnection(String dbType){
        return switch (dbType) {
            case "MySQL" -> new MySQLConnection();
            case "Oracle" -> new OracleConnection();
            default -> throw new IllegalArgumentException("Unsupported databse type");
        };
    }
}

public class main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = DatabaseConnectionFactory.createConnection("MySQL");
        dbConnection.connect();
        dbConnection = DatabaseConnectionFactory.createConnection("Oracle");
        dbConnection.connect();
    }
}
