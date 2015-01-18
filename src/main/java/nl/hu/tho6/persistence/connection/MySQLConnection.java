package nl.hu.tho6.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection implements DBConnection {
    protected final static String DB_DRIV     = "com.mysql.jdbc.Driver";
    protected              String databaseURL = "jdbc:mysql://databaseIP:3306/THO6";

    public MySQLConnection() {
        try {
            Class.forName(DB_DRIV).newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Connection connect() {
        Connection con = null; try {
            con = DriverManager.getConnection(databaseURL, "root", "");
        } catch (Exception ex) {
            System.out.println("Kan niet verbinden met de database" + ex);
        } return con;
    }

    @Override
    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Kan databaseverbinding niet sluiten" + ex);
        }

    }
}
