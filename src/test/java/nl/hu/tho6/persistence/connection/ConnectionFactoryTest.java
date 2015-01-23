package nl.hu.tho6.persistence.connection;

import nl.hu.tho6.persistence.connection.ConnectionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.Connection;
import java.sql.SQLException;

/** 
* ConnectionFactory Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 23, 2015</pre> 
* @version 1.0 
*/ 
public class ConnectionFactoryTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createConnection() 
* 
*/ 
@Test
public void testCreateConnection() throws Exception {
    try{
        Connection con = ConnectionFactory.getConnection();
        boolean bool = con.isValid(10);
    }
    catch(Exception se){
        se.printStackTrace();
    }


} 

/** 
* 
* Method: getConnection() 
* 
*/ 
@Test
public void testGetConnection() throws Exception {
    try{
        Connection con = ConnectionFactory.getConnection();
        boolean bool = con.isValid(10);
    }
    catch(Exception se){
        se.printStackTrace();
    }
} 


} 
