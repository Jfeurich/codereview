package nl.hu.tho4.domain.businessrule;

public class Value {
    private String waardeNaam;
    private String valueType;
    private String value;

    public Value(){

    }

    public Value(String waardeNaam, String valueType, String value) {
        this.waardeNaam = waardeNaam;
        this.valueType = valueType;
        this.value = value;
    }

    public String getWaardeNaam() {
        return waardeNaam;
    }

    public void setWaardeNaam(String waardeNaam) {
        this.waardeNaam = waardeNaam;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
