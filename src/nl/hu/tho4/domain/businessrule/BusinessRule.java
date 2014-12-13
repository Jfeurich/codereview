package nl.hu.tho4.domain.businessrule;

public abstract class BusinessRule {
    private String   ruleNaam;
    private String   error;
    private String   errorType;
    private String   code;
    private Operator operator;
    private Value    value1, value2;
    private Attribute attribute1, attribute2;

    public BusinessRule(String ruleNaam, String error, String errorType, String code, Operator operator, Value value1, Value value2, Attribute attribute1, Attribute attribute2) {
        this.ruleNaam = ruleNaam;
        this.error = error;
        this.errorType = errorType;
        this.code = code;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    public String getRuleNaam() {
        return ruleNaam;
    }

    public void setRuleNaam(String ruleNaam) {
        this.ruleNaam = ruleNaam;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Value getValue1() {
        return value1;
    }

    public void setValue1(Value value1) {
        this.value1 = value1;
    }

    public Value getValue2() {
        return value2;
    }

    public void setValue2(Value value2) {
        this.value2 = value2;
    }

    public Attribute getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(Attribute attribute1) {
        this.attribute1 = attribute1;
    }

    public Attribute getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(Attribute attribute2) {
        this.attribute2 = attribute2;
    }
}
