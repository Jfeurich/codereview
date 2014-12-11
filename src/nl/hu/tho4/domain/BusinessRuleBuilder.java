package nl.hu.tho4.domain;

public class BusinessRuleBuilder {
	public BusinessRule attCompareRule(String ruleNaam,
	String error,Operator o, String errorType,Attribute a,Attribute a2,BusinessRuleType businessruletype) {
		BusinessRule rule = new BusinessRule();
		rule.setRuleNaam(ruleNaam);
		rule.setError(error);
		rule.setOperator(o);
		rule.setErrorType(errorType);
		rule.addAttribute(a);
		rule.addAttribute(a2);
		rule.setBusinessruletype(businessruletype);
		return rule;
	}
	public BusinessRule attRangeRule(String ruleNaam, String error, Operator o,String errorType, Attribute a, Value v1, Value v2,BusinessRuleType businessruletype) {
		BusinessRule rule = new BusinessRule();
		rule.setRuleNaam(ruleNaam);
		rule.setError(error);
		rule.setOperator(o);
		rule.setErrorType(errorType);
		rule.addAttribute(a);
		rule.addValue(v1);
		rule.addValue(v2);
		rule.setBusinessruletype(businessruletype);
		return rule;
	}
	/*
	public BusinessRule attListRule(String ruleNaam, String error, Operator o ,String  errorType, Attribute a,BusinessRuleType businessruletype) {
		return rule;
	}
	public BusinessRule tupleCompareRule(String ruleNaam,String  error, Operator o ,String errorType, Attribute a1, Attribute a2,BusinessRuleType businessruletype){
		return rule;
	}
	public BusinessRule interEntityCompareRule(String ruleNaam,String error,Operator o, String errorType, Attribute a1, Attribute a2,BusinessRuleType businessruletype){
		return rule;
	}
	public BusinessRule entityOtherRule(String ruleNaam, String error, Operator o, String errorType, Attribute a, String s,BusinessRuleType businessruletype){
		return rule;
	}
	*/
}
