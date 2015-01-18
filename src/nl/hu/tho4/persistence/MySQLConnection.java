package nl.hu.tho4.persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class MySQLConnection implements Connection{
	protected final static String DB_DRIV = "com.mysql.jdbc.Driver";
	protected String databaseURL = "jdbc:mysql://databaseIP:3306/THO6";

	public MySQLConnection() {
		try{
			Class.forName(DB_DRIV).newInstance();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
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
	public void closeConnection(Connection con) {
		try{
			con.close();
		}
		catch(Exception ex){
			System.out.println("Kan databaseverbinding niet sluiten" + ex);
		}	
		
	}
}
