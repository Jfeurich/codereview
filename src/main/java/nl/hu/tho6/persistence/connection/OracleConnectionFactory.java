package nl.hu.tho6.persistence.connection;

public class OracleConnectionFactory implements ConnectionFactory {
    @Override
    public DBConnection getConnection() {
        // TODO Auto-generated method stub
        return new OracleConnection();
    }
}
