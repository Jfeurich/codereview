package nl.hu.tho4.domain.businessrule;

import static org.junit.Assert.*;

import org.junit.*;

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
