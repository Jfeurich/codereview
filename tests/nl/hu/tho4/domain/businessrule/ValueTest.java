package nl.hu.tho4.domain.businessrule;

import org.junit.*;
import static org.junit.Assert.*;

public class ValueTest{
    private Value testValue;

    @Before
    public void setUp() {
        testValue = new Value();
        testValue.setWaardeNaam("Naam");
        testValue.setValueType("Type");
        testValue.setValue("Value");
    }

    @Test
    public void testGetWaardeNaam() {
        assertEquals("Naam", testValue.getWaardeNaam());
    }

    @Test
    public void testSetWaardeNaam() {
        testValue.setWaardeNaam("Value");
        assertNotEquals("Naam", testValue.getWaardeNaam());
        assertEquals("Value", testValue.getWaardeNaam());
    }

    @Test
    public void testGetValueType() {
        assertEquals("Type", testValue.getValueType());
    }

    @Test
    public void testSetValueType() {
        testValue.setValueType("Value");
        assertNotEquals("Type", testValue.getValueType());
        assertEquals("Value", testValue.getValueType());
    }

    @Test
    public void testGetValue() {
        assertEquals("Value", testValue.getValue());
    }

    @Test
    public void testSetValue() {
        testValue.setValue("Value2");
        assertNotEquals("Value", testValue.getValue());
        assertEquals("Value2", testValue.getValue());
    }

    @After
    public void tearDown() {
        testValue = null;
    }

}
