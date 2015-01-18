package nl.hu.tho6.persistence.connection;

import java.sql.Connection;

public interface DBConnection {
    public Connection connect();

    public void closeConnection(Connection con);

}
