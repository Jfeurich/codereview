package nl.hu.tho4.domain.tempdel;

import nl.hu.tho4.domain.BusinessRule;
import org.stringtemplate.v4.ST;

public class TemplateOld {
    private String templateNaam;
    private String templateType;
    private String businessRuleType;

    public TemplateOld() {
    }
//
//    public String sqlbusinessruletemplate(String trigger_name, String col_name, String table_name, String declaration_statements, String executable_statements, String exception_statements) {
//        ST sqlbusinessruletemplate = new ST("CREATE OR REPLACE TRIGGER $trigger_name$ " +
//                "BEFORE INSERT OR UPDATE " +
//                "OF $col_name$ ON $table_name$ " +
//                " WHEN $condition$ " +
//                " $declaration_statements$ " +
//                "BEGIN " +
//                " $executable_statements$ " +
//                "EXCEPTION " +
//                " $exception_statements$ " +
//                "END;");
//        sqlbusinessruletemplate.add("trigger_name", trigger_name);
//        sqlbusinessruletemplate.add("col_name", col_name);
//        sqlbusinessruletemplate.add("table_name", table_name);
//        sqlbusinessruletemplate.add("declaration_statements", declaration_statements);
//        sqlbusinessruletemplate.add("executable_statements", executable_statements);
//        sqlbusinessruletemplate.add("exception_statements", exception_statements);
//        return sqlbusinessruletemplate.render();
//
//    }

    public ST sqlbusinessruletemplateNew(BusinessRule rule) {
        ST sqlbusinessruletemplate = new ST("CREATE OR REPLACE TRIGGER <trigger_name> " +
                "BEFORE INSERT OR UPDATE " +
                "OF <col_name> ON <table_name> " +
                " WHEN <rule.att1> <rule.operator> <rule.att2> " +
                " <declaration_statements> " +
                "BEGIN " +
                " <executable_statements> " +
                "EXCEPTION " +
                " <exception_statements> " +
                "END;");
//        sqlbusinessruletemplate.add("trigger_name", trigger_name());
//        sqlbusinessruletemplate.add("col_name", col_name());
//        sqlbusinessruletemplate.add("table_name", table_name());
//        sqlbusinessruletemplate.add("declaration_statements", declaration_statements());
//        sqlbusinessruletemplate.add("executable_statements", executable_statements());
//        sqlbusinessruletemplate.add("exception_statements", exception_statements());
        return sqlbusinessruletemplate;
    }

    public String createTriggerName() {
        String trigger_name = null;
        return trigger_name;
    }

    public String col_name() {
        String col_name = null;
        return col_name;
    }

    public String table_name() {
        String table_name = null;
        return table_name;
    }

    public String declaration_statements() {
        String declaration_statements = null;
        return declaration_statements;
    }

    public String executable_statements() {
        String executable_statements = null;
        return executable_statements;
    }

    public String exception_statements() {
        String exception_statements = null;
        return exception_statements;
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
