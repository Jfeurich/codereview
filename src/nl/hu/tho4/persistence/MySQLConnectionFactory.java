package nl.hu.tho4.persistence;


public class MySQLConnectionFactory implements ConnectionFactory{
	@Override
	public DBConnection getConnection() {
		return new MySQLConnection();
	}
}
