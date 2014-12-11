package nl.hu.tho4.domain.template;

public class Template {
	private String templateNaam;
	private String templateType;
	private String businessRuleType;
	public Template() {
	}
	public Template makeTemplate(){
		Template t = new Template();
		
		return t;
	}
	public String getTemplateNaam() {
		return templateNaam;
	}
	public void setTemplateNaam(String templateNaam) {
		this.templateNaam = templateNaam;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getBusinessRuleType() {
		return businessRuleType;
	}
	public void setBusinessRuleType(String businessRuleType) {
		this.businessRuleType = businessRuleType;
	}

}
