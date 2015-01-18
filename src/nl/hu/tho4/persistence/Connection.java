package nl.hu.tho4.persistence;


public interface Connection {	
	public void connect();
	public void closeConnection(Connection con);

}
