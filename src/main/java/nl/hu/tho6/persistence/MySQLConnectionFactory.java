package nl.hu.tho6.persistence;

public class MySQLConnectionFactory implements ConnectionFactory {
    @Override
    public DBConnection getConnection() {
        return new MySQLConnection();
    }
}
