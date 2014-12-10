package nl.hu.tho4.domain;

public class Attribute {
	private String attributeNaam;
	private String dbSchema;
	private String tabel;
	private String kolom;
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
}
