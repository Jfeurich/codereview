package nl.hu.tho4.domain.template;

import org.stringtemplate.v4.ST;

public class Template {
	private String templateNaam;
	private String templateType;
	private String businessRuleType;
	public Template() {
	}
	public ST sqlbusinessruletemplate(){ 
		ST sqlbusinessruletemplate = new ST(
			"CREATE OR REPLACE TRIGGER $trigger_name$ "+
			"BEFORE INSERT OR UPDATE "+
			"OF $col_name$ " +
			"ON $table_name$ " +
			" WHEN $condition$ " +
			" $declaration_statements$ " +
			"BEGIN " +
			" $executable_statements$ " +
			"EXCEPTION " +
			" $exception_statements$ " +
			"END;"
		);
		sqlbusinessruletemplate.add("trigger_name","");
		sqlbusinessruletemplate.add("col_name","");
		sqlbusinessruletemplate.add("table_name","");
		sqlbusinessruletemplate.add("declaration_statements","");
		sqlbusinessruletemplate.add("executable_statements","");
		sqlbusinessruletemplate.add("exception_statements","");
		return sqlbusinessruletemplate;

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
