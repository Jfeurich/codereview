package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.utils.stringtemplate.StringTemplate;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    private Translator translator;

    public static Generator getInstance() {
        return ourInstance;
    }

    //TODO refactor
    public String generate(BusinessRule businessRule) {
        String language = businessRule.getLanguage().toLowerCase();
        StringTemplate templateForLanguage = getTemplateForLanguage(language);

        boolean unfilledAttributes = true;
        Map<String, String> attributes = templateForLanguage.getAttributes();

        while (unfilledAttributes) {
            for (Map.Entry<String, String> attribute : attributes.entrySet()) {
                if (attribute.getValue().equals("empty")) {
                    if (!execute(attribute.getKey(), businessRule, language, templateForLanguage)) {
                        templateForLanguage.setAttribute(attribute.getKey(), translator.getTranslationForElement(attribute.getKey(), language));
                    }
                }
            }

            templateForLanguage = new StringTemplate(templateForLanguage.render());
            attributes = templateForLanguage.getAttributes();

            unfilledAttributes = (!templateForLanguage.checkAllAttributes());
        }

        return templateForLanguage.render();
    }

    private Generator() {
        translator = Translator.getInstance();
    }

    private StringTemplate getTemplateForLanguage(String language) {
        return new StringTemplate(translator.getTranslationForElement("Template", language));
    }

    private boolean execute(String command, BusinessRule businessRule, String language, StringTemplate templateForLanguage) {
        boolean commandExecuted = false;

        if (command.equals("TriggerName")) {
            templateForLanguage.setAttribute(command, generateTriggerName(businessRule));
            commandExecuted = true;
        } else if (command.equals("TimeOperator")) {
            templateForLanguage.setAttribute(command, generateTimpeOperator(businessRule));
            commandExecuted = true;
        } else if (command.equals("TableName")) {
            templateForLanguage.setAttribute(command, generateTableName(businessRule));
            commandExecuted = true;
        } else if (command.equals("Variables")) {
            templateForLanguage.setAttribute(command, generateVariables(businessRule));
            commandExecuted = true;
        } else if (command.equals("Conditions")) {
            templateForLanguage.setAttribute(command, generateConditions(businessRule,language));
            commandExecuted = true;
        } else if (command.equals("Error")) {
            templateForLanguage.setAttribute(command, generateError(businessRule,language));
            commandExecuted = true;
        } else if (command.equals("ErrorMessage")) {
            templateForLanguage.setAttribute(command, getErrorMessage(businessRule));
            commandExecuted = true;
        } else if (command.equals("LoadVariables")) {
            templateForLanguage.setAttribute(command, generateVariableLoading(businessRule,language));
            commandExecuted = true;
        } else if (command.equals("Table1")) {
            templateForLanguage.setAttribute(command, getTable1(businessRule));
            commandExecuted = true;
        } else if (command.equals("Table2")) {
            templateForLanguage.setAttribute(command, getTable2(businessRule));
            commandExecuted = true;
        } else if (command.equals("Column1")) {
            templateForLanguage.setAttribute(command, getColumn1(businessRule));
            commandExecuted = true;
        } else if (command.equals("Column2")) {
            templateForLanguage.setAttribute(command, getColumn2(businessRule));
            commandExecuted = true;
        }  else if (command.equals("Reference1")) {
            templateForLanguage.setAttribute(command, getReference1(businessRule));
            commandExecuted = true;
        }  else if (command.equals("Reference2")) {
            templateForLanguage.setAttribute(command, getReference2(businessRule));
            commandExecuted = true;
        }

        return commandExecuted;
    }

    private String getReference1(BusinessRule businessRule) {
        return businessRule.getAttribute1().getReference();
    }

    private String getReference2(BusinessRule businessRule) {
        return businessRule.getAttribute2().getReference();
    }

    private String getColumn1(BusinessRule businessRule) {
        return businessRule.getAttribute1().getKolom();
    }

    private String getColumn2(BusinessRule businessRule) {
        return businessRule.getAttribute2().getKolom();
    }

    private String getTable1(BusinessRule businessRule) {
        return businessRule.getAttribute1().getTabel();
    }

    private String getTable2(BusinessRule businessRule) {
        return businessRule.getAttribute2().getTabel();
    }

    private String generateVariableLoading(BusinessRule businessRule, String language) {
        if(businessRule.getAttribute1() != null && businessRule.getAttribute2() != null) {
            if (!businessRule.getAttribute1().getTabel().equals(businessRule.getAttribute2().getTabel())) {
                return translator.getTranslationForElement("LoadOtherTableIntoVariable", language);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private String generateTriggerName(BusinessRule businessRule) {
        String code = "BRG_TARGET_" + businessRule.getAttribute1().getTabel().substring(0,3) + "_TRIGGER" + businessRule.getRuleID();
        return code;
    }

    private String generateTimpeOperator(BusinessRule businessRule) {
        if(businessRule.getBusinessruletype() == 9){
            return "update";
        } else {
            return "insert or update";
        }
    }

    private String generateTableName(BusinessRule businessRule) {
        String tableName = businessRule.getAttribute1().getTabel();
        return tableName;
    }

    private String generateError(BusinessRule businessRule, String language) {
        String error = "";
        if(businessRule.getErrorType().equals("2")){
            error += translator.getTranslationForElement("ErrorFunction",language);
        } else {
            error += translator.getTranslationForElement("DefaultError",language);
        }
        return error;
    }

    private String getErrorMessage(BusinessRule businessRule){
        return businessRule.getError();
    }

    //TODO refactor
    private String generateConditions(BusinessRule businessRule, String language) {
        String conditions = "( :new." + businessRule.getAttribute1().getKolom();
        if(businessRule.getOperator().getNaam().equals("Between") || businessRule.getOperator().getNaam().equals("NotBetween")){
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("GreaterThanOrEqualsTo",language);
            } else {
                conditions += " " + translator.getTranslationForElement("LesserThan",language);
            }
            conditions += " " + businessRule.getValue1().getValue();
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("And",language);
            } else {
                conditions += " " + translator.getTranslationForElement("Or",language);
            }
            conditions += " :new." + businessRule.getAttribute1().getKolom();
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("LesserThanOrEqualsTo",language);
            } else {
                conditions += " " + translator.getTranslationForElement("GreaterThan",language);
            }
            conditions += " " + businessRule.getValue2().getValue();
        } else {
            conditions += " " + translator.getTranslationForElement(businessRule.getOperator().getNaam(), language);
            if(businessRule.getOperator().getNaam().equals("In") || businessRule.getOperator().getNaam().equals("NotIn")){
                String[] tags = businessRule.getValue1().getValue().split(":");
                conditions += " (";
                conditions += "'" + tags[0] + "'";
                for (int i = 1; i < tags.length; i++) {
                    conditions += ",'" + tags[i] + "'";
                }
                conditions += " )";
            } else {
                if (businessRule.getAttribute2() != null) {
                    if (!businessRule.getAttribute1().getTabel().equals(businessRule.getAttribute2().getTabel())) {
                        conditions += " " + translator.getTranslationForElement("Variable",language);
                    } else {
                        conditions += " :new." + businessRule.getAttribute2().getKolom();
                    }
                } else if (businessRule.getValue1() != null) {
                    conditions += " '" + businessRule.getValue1().getValue() + "'";
                }
            }
        }
        conditions += " )";
        return conditions;
    }

    //TODO implement method
    private String generateVariables(BusinessRule businessRule) {
        String variables = "";
        if(businessRule.getAttribute1() != null && businessRule.getAttribute2() != null){
            if(!businessRule.getAttribute1().getTabel().equals(businessRule.getAttribute2().getTabel())
                    || !businessRule.getAttribute1().getKolom().equals(businessRule.getAttribute2().getKolom())){
                variables += "v_temp " + businessRule.getAttribute1().getTabel() + "." + businessRule.getAttribute1().getKolom() + "%type;";
            }
        }
        return variables;
    }
}
