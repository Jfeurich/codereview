package nl.hu.tho4.persistence;

public class OracleConnectionFactory implements ConnectionFactory{
	@Override
	public Connection getConnection() {
		return new OracleConnection();
	}
}
