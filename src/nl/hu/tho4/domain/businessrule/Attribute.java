package nl.hu.tho4.domain.businessrule;

public class Attribute {
    private String attributeNaam;
    private String dbSchema;
    private String tabel;
    private String kolom;
    private String value;

    public Attribute(String attributeNaam, String dbSchema, String tabel, String kolom, String value) {
        this.attributeNaam = attributeNaam;
        this.dbSchema = dbSchema;
        this.tabel = tabel;
        this.kolom = kolom;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
