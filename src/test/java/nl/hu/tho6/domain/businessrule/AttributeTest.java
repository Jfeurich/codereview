package nl.hu.tho6.domain.businessrule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AttributeTest {
    private Attribute testAttribute;

    @Before
    public void setUp() {
        testAttribute = new Attribute();
        testAttribute.setAttributeNaam("Naam");
        testAttribute.setDbSchema("Schema");
        testAttribute.setTabel("Tabel");
        testAttribute.setKolom("Kolom");
        testAttribute.setAttributeID(0);
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
    public void testGetAttributeID() {
        assertEquals(0, testAttribute.getAttributeID());
    }

    @Test
    public void testSetAttributeID() {
        testAttribute.setAttributeID(1);
        assertNotEquals(0, testAttribute.getAttributeID());
        assertEquals(1, testAttribute.getAttributeID());
    }

    @After
    public void Teardown() {
        testAttribute = null;
    }
}