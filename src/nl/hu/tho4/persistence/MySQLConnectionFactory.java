package nl.hu.tho4.persistence;


public class MySQLConnectionFactory implements ConnectionFactory{
	@Override
	public Connection getConnection() {
		return new MySQLConnection();
	}
}
