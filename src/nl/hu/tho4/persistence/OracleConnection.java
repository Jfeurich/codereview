package nl.hu.tho4.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection implements DBConnection {
	protected final static String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	protected String databaseURL = "jdbc:oracle://localhost:3306/THO6";
	@Override
	public Connection connect() {
		Connection con = null;
		try{
			con = DriverManager.getConnection(databaseURL, "root", "");
		}
		catch(Exception ex){
			System.out.println("Kan niet verbinden met de database" + ex);
		}
		return con;
		
	}

	@Override
	public void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		
	}

}
