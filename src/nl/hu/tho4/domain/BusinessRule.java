package nl.hu.tho4.domain;

import java.util.ArrayList;

public abstract class BusinessRule {
	private String ruleNaam;
	private String error;
	private String errorType;
	private String code;
	private BusinessRuleType businessruletype;
	private ArrayList<Operator> operators = new ArrayList<>();
	private ArrayList<Attribute> attributes = new ArrayList<>();
	private ArrayList<Value> values = new ArrayList<>();

	public void addAttribute(Attribute a){
		attributes.add(a);
	}
	public void addValue(Value v){
		values.add(v);
	}
	public void setOperator(Operator o){
		operators.add(o);
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
	public BusinessRuleType getBusinessruletype() {
		return businessruletype;
	}
	public void setBusinessruletype(BusinessRuleType businessruletype) {
		this.businessruletype = businessruletype;
	}
	public String getCode() {
		return code;
	}

	public BusinessRule parseStringRepresentation;

	public void setCode(String code) {
		this.code = code;
	}

}
