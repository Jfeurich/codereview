package nl.hu.tho4.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection implements DBConnection {
	protected final static String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	protected String databaseURL = "jdbc:oracle://localhost:3306/THO6";
	public OracleConnection(){
			try{
			Class.forName(DB_DRIV).newInstance();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	@Override
	public void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Connection connect() {
		// TODO Auto-generated method stub
		return null;
	}

}
