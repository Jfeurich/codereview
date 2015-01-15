package nl.hu.tho4.tests;

import java.sql.Connection;
import java.util.Properties;

import nl.hu.tho4.persistence.MySQLConnection;
import oracle.jdbc.oci.OracleOCIConnection;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * The class <code>MySQLConnectionTest</code> contains tests for the class <code>{@link MySQLConnection}</code>.
 *
 * @generatedBy CodePro at 1/14/15 2:47 PM
 * @author jayfeurich
 * @version $Revision: 1.0 $
 */
public class MySQLConnectionTest {
	/**
	 * Run the MySQLConnection() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testMySQLConnection_1()
		throws Exception {

		MySQLConnection result = new MySQLConnection();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.connect());
	}

	/**
	 * Run the MySQLConnection() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testMySQLConnection_2()
		throws Exception {

		MySQLConnection result = new MySQLConnection();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.connect());
	}

	/**
	 * Run the MySQLConnection() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testMySQLConnection_3()
		throws Exception {

		MySQLConnection result = new MySQLConnection();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.connect());
	}

	/**
	 * Run the MySQLConnection() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testMySQLConnection_4()
		throws Exception {

		MySQLConnection result = new MySQLConnection();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.connect());
	}

	/**
	 * Run the void closeConnection(Connection) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testCloseConnection_1()
		throws Exception {
		MySQLConnection fixture = new MySQLConnection();
		//fixture.databaseURL = "";
		Connection con = new OracleOCIConnection("", new Properties(), new Object());

		fixture.closeConnection(con);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ClassCastException: java.lang.Object cannot be cast to oracle.jdbc.driver.OracleDriverExtension
		//       at oracle.jdbc.driver.OracleOCIConnection.<init>(OracleOCIConnection.java:44)
		//       at oracle.jdbc.oci.OracleOCIConnection.<init>(OracleOCIConnection.java:36)
	}

	/**
	 * Run the void closeConnection(Connection) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testCloseConnection_2()
		throws Exception {
		MySQLConnection fixture = new MySQLConnection();
		//fixture.databaseURL = "";
		Connection con = new OracleOCIConnection("", new Properties(), new Object());

		fixture.closeConnection(con);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.ClassCastException: java.lang.Object cannot be cast to oracle.jdbc.driver.OracleDriverExtension
		//       at oracle.jdbc.driver.OracleOCIConnection.<init>(OracleOCIConnection.java:44)
		//       at oracle.jdbc.oci.OracleOCIConnection.<init>(OracleOCIConnection.java:36)
	}

	/**
	 * Run the Connection connect() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Test
	public void testConnect_1()
		throws Exception {
		MySQLConnection fixture = new MySQLConnection();
		//fixture.databaseURL = "";

		Connection result = fixture.connect();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 1/14/15 2:47 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MySQLConnectionTest.class);
	}
}