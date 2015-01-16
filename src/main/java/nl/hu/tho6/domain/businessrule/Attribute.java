package nl.hu.tho6.domain.businessrule;

public class Attribute {
    private String attributeNaam;
    private String dbSchema;
    private String tabel;
    private String kolom;
    private int attributeID;

    public Attribute() {

    }

    public Attribute(String attributeNaam, String dbSchema, String tabel, String kolom, int attributeID) {
        this.attributeNaam = attributeNaam;
        this.dbSchema = dbSchema;
        this.tabel = tabel;
        this.kolom = kolom;
        this.setAttributeID(attributeID);
    }

    public String getAttributeNaam() {
        return attributeNaam;
    }

    public void setAttributeNaam(String attributeNaam) {
        this.attributeNaam = attributeNaam;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public String getTabel() {
        return tabel;
    }

    public void setTabel(String tabel) {
        this.tabel = tabel;
    }

    public String getKolom() {
        return kolom;
    }

    public void setKolom(String kolom) {
        this.kolom = kolom;
    }

    public int getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(int attributeID) {
        this.attributeID = attributeID;
    }
}
