package nl.hu.tho4.persistence;

import java.sql.Connection;


public interface DBConnection {	
	public Connection connect();
	public void closeConnection(Connection con);

}
