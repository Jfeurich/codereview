package domain;

public class BusinessRuleBuilder {
	public BusinessRule attCompareRule(String ruleNaam,String error,Operator o, String errorType,Attribute a,Attribute a2) {
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
	public BusinessRule attRangeRule(String ruleNaam, String error, Operator o,String errorType, Attribute a, Value v1, Value v2) {
		return null;
	}
	public BusinessRule attListRule(String ruleNaam, String error, Operator o ,String  errorType, Attribute a) {
		return null;
	}
	public BusinessRule tupleCompareRule(String ruleNaam,String  error, Operator o ,String errorType, Attribute a1, Attribute a2){
		return null;
	}
}
