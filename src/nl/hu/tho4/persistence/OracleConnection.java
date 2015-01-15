package nl.hu.tho4.persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection implements DBConnection {
	protected final static String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
	protected String databaseURL = "jdbc:oracle://databaseIP:3306/THO6";
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
		try{
			con.close();
		}
		catch(Exception ex){
			System.out.println("Kan databaseverbinding niet sluiten" + ex);
		}	
		
	}
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

}
