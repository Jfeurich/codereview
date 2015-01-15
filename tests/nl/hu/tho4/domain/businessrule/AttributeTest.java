package nl.hu.tho4.domain.businessrule;

import org.junit.*;
import static org.junit.Assert.*;

public class AttributeTest {
    private Attribute testAttribute;


    @Before
    public void setUp() {
        testAttribute = new Attribute();
        testAttribute.setAttributeNaam("Naam");
        testAttribute.setDbSchema("Schema");
        testAttribute.setTabel("Tabel");
        testAttribute.setKolom("Kolom");
        testAttribute.setValue("Value");
    }

    @Test
    public void testGetAttributeNaam() {
        assertEquals("Naam", testAttribute.getAttributeNaam());
    }

    @Test
    public void testSetAttributeNaam() {
        testAttribute.setAttributeNaam("Attribute");
        assertNotEquals("Naam", testAttribute.getAttributeNaam());
        assertEquals("Attribute", testAttribute.getAttributeNaam());
    }

    @Test
    public void testGetDbSchema() {
        assertEquals("Schema", testAttribute.getDbSchema());
    }

    @Test
    public void testSetDbSchema() {
        testAttribute.setDbSchema("Attribute");
        assertNotEquals("Schema", testAttribute.getDbSchema());
        assertEquals("Attribute", testAttribute.getDbSchema());
    }

    @Test
    public void testGetTabel() {
        assertEquals("Tabel", testAttribute.getTabel());
    }

    @Test
    public void testSetTabel() {
        testAttribute.setTabel("Attribute");
        assertNotEquals("Tabel", testAttribute.getTabel());
        assertEquals("Attribute", testAttribute.getTabel());
    }

    @Test
    public void testGetKolom() {
        assertEquals("Kolom", testAttribute.getKolom());
    }

    @Test
    public void testSetKolom() {
        testAttribute.setKolom("Attribute");
        assertNotEquals("Kolom", testAttribute.getKolom());
        assertEquals("Attribute", testAttribute.getKolom());
    }

    @Test
    public void testGetValue() {
        assertEquals("Value", testAttribute.getValue());
    }

    @Test
    public void testSetValue() {
        testAttribute.setValue("Attribute");
        assertNotEquals("Value", testAttribute.getValue());
        assertEquals("Attribute", testAttribute.getValue());
    }

    @After
    public void Teardown(){
        testAttribute = null;
    }
}
