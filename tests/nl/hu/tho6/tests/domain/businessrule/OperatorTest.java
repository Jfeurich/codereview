package nl.hu.tho6.tests.domain.businessrule;

import nl.hu.tho6.domain.businessrule.Operator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OperatorTest {
    private Operator testOperator;

    @Before
    public void setUp() {
        testOperator = new Operator();
        testOperator.setNaam("Naam");
        testOperator.setOperatorType("Type");
    }

    @Test
    public void testGetNaam() {
        assertEquals("Naam", testOperator.getNaam());
    }

    @Test
    public void testSetNaam() {
        testOperator.setNaam("Operator");
        assertNotEquals("Naam", testOperator.getNaam());
        assertEquals("Operator", testOperator.getNaam());
    }

    @Test
    public void testGetOperatorType() {
        assertEquals("Type", testOperator.getOperatorType());
    }

    @Test
    public void testSetOperatorType() {
        testOperator.setOperatorType("Operator");
        assertNotEquals("Type", testOperator.getOperatorType());
        assertEquals("Operator", testOperator.getOperatorType());
    }

    @After
    public void tearDown() {
        testOperator = null;
    }

}
