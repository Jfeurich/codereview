package nl.hu.tho6.domain.businessrule;

public class Operator {
    private String naam;
    private String operatorType;
    private int operatorID;

    public Operator() {

    }

    public Operator(String naam, String operatorType) {
        this.naam = naam;
        this.operatorType = operatorType;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public int getOperatorID() { return operatorID; }

    public void setOperatorID(int operatorID) {this.operatorID = operatorID; }
}
