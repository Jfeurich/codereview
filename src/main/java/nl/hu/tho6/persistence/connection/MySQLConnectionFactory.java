package nl.hu.tho6.persistence.connection;

public class MySQLConnectionFactory implements ConnectionFactory {
    @Override
    public DBConnection getConnection() {
        return new MySQLConnection();
    }
}
