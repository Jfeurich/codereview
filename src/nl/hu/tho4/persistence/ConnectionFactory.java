package nl.hu.tho4.persistence;
import nl.hu.tho4.persistence.DBConnection;

public interface ConnectionFactory {

	DBConnection getConnection();

}
