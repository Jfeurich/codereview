package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.utils.stringtemplate.StringTemplate;

import java.lang.String;
import java.util.Map;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    private Translator translator;

    private Generator() {
        translator = Translator.getInstance();
    }

    public static Generator getInstance() {
        return ourInstance;
    }

    public String generate(BusinessRule businessRule) {
        String language = businessRule.getLanguage().toLowerCase();
        StringTemplate templateForLanguage = getTemplateForLanguage(language);

        boolean unfilledAttributes = true;
        Map<String, String> attributes = templateForLanguage.getAttributes();

        templateForLanguage = fillAttributesForBusinessRule(businessRule, language, templateForLanguage, unfilledAttributes, attributes);

        return templateForLanguage.render();
    }

    private StringTemplate fillAttributesForBusinessRule(BusinessRule businessRule, String language, StringTemplate templateForLanguage, boolean hasUnfilledAttributes, Map<String, String> attributes) {
        while (hasUnfilledAttributes) {
            fillAttribute(businessRule, language, templateForLanguage, attributes);

            templateForLanguage = new StringTemplate(templateForLanguage.render());
            attributes = templateForLanguage.getAttributes();

            hasUnfilledAttributes = !templateForLanguage.checkAllAttributes();
        }
        return templateForLanguage;
    }

    private void fillAttribute(BusinessRule businessRule, String language, StringTemplate templateForLanguage, Map<String, String> attributes) {
        for (Map.Entry<String, String> attribute : attributes.entrySet()) {
            if (isAttributeEmpty(attribute) && isNotExecuted(businessRule, language, templateForLanguage, attribute)) {
                setTemplateAttribute(language, templateForLanguage, attribute);
            }
        }
    }

    private boolean isNotExecuted(BusinessRule businessRule, String language, StringTemplate templateForLanguage, Map.Entry<String, String> attribute) {
        return !execute(attribute.getKey(), businessRule, language, templateForLanguage);
    }

    private boolean isAttributeEmpty(Map.Entry<String, String> attribute) {
        return attribute.getValue().equals("empty");
    }

    private void setTemplateAttribute(String language, StringTemplate templateForLanguage, Map.Entry<String, String> attribute) {
        templateForLanguage.setAttribute(attribute.getKey(), translator.getTranslationForElement(attribute.getKey(), language));
    }

    private StringTemplate getTemplateForLanguage(String language) {
        return new StringTemplate(translator.getTranslationForElement("Template", language));
    }

    private void execute(String command, BusinessRule businessRule, String language, StringTemplate templateForLanguage) {
        switch(command) {
            case "TriggerName":
                templateForLanguage.setAttribute(command, generateTriggerName(businessRule));
                break;
            case "TimeOperator":
                templateForLanguage.setAttribute(command, generateTimpeOperator(businessRule));
                break;
            case "TableName":
                templateForLanguage.setAttribute(command, generateTableName(businessRule));
                break;
            case "Variables":
                templateForLanguage.setAttribute(command, generateVariables(businessRule));
                break;
            case "Conditions":
                templateForLanguage.setAttribute(command, generateConditions(businessRule,language));
                break;
            case "Error":
                templateForLanguage.setAttribute(command, generateError(businessRule,language));
                break;
            case "ErrorMessage":
                templateForLanguage.setAttribute(command, getErrorMessage(businessRule));
                break;
            case "LoadVariables":
                templateForLanguage.setAttribute(command, generateVariableLoading(businessRule,language));
                break;
            case "Table1":
                templateForLanguage.setAttribute(command, businessRule.getAttribute1().getTabel());
                break;
            case "Table2":
                templateForLanguage.setAttribute(command, businessRule.getAttribute2().getTabel());
                break;
            case "Column1":
                templateForLanguage.setAttribute(command, businessRule.getAttribute1().getKolom());
                break;
            case "Column2":
                templateForLanguage.setAttribute(command, businessRule.getAttribute2().getKolom());
                break;
            case "Reference1":
                templateForLanguage.setAttribute(command, businessRule.getAttribute1().getReference());
                break;
            case "Reference2":
                templateForLanguage.setAttribute(command, businessRule.getAttribute2().getReference());
                break;
            default:
                throw new IllegalArgumentException("Unknown command");
                break;
        }
    }

    private String generateVariableLoading(BusinessRule businessRule, String language) {
        if(hasTwoAttributes(businessRule)) {
            if (hasDifferentTablesForAttributes(businessRule)) {
                return translator.getTranslationForElement("LoadOtherTableIntoVariable", language);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private boolean hasDifferentTablesForAttributes(BusinessRule businessRule) {
        return !businessRule.getAttribute1().getTabel().equals(businessRule.getAttribute2().getTabel());
    }

    private boolean hasTwoAttributes(BusinessRule businessRule) {
        return businessRule.getAttribute1() != null && businessRule.getAttribute2() != null;
    }

    private String generateTriggerName(BusinessRule businessRule) {
        return "BRG_TARGET_" + businessRule.getAttribute1().getTabel().substring(0,3) + "_TRIGGER" + businessRule.getRuleID();
    }

    private String generateTimpeOperator(BusinessRule businessRule) {
        if(businessRule.getBusinessruletype() == 9){
            return "update";
        } else {
            return "insert or update";
        }
    }

    private String generateTableName(BusinessRule businessRule) {
        return businessRule.getAttribute1().getTabel();
    }

    private String generateError(BusinessRule businessRule, String language) {
        String error = "";
        if("2".equals(businessRule.getErrorType())){
            error += addMessageToError(language,, "ErrorFunction");
        } else {
            error += addMessageToError(language,, "DefaultError");
        }
        return error;
    }

    private String addMessageToError(String language, String errorFunction) {
        return translator.getTranslationForElement(errorFunction, language);
    }

    private String getErrorMessage(BusinessRule businessRule){
        return businessRule.getError();
    }

    //TODO refactor
    private String generateConditions(BusinessRule businessRule, String language) {
        String conditions = "( :new." + businessRule.getAttribute1().getKolom();
        if(isOperatorName(businessRule, "Between", "NotBetween")){
            conditions = handleBetweenOrNotBetweenOperator(businessRule, language, conditions);
        } else {
            conditions += " " + translator.getTranslationForElement(businessRule.getOperator().getNaam(), language);
            if(isOperatorName(businessRule, "In", "NotIn")){
                conditions = handleInOrNotInOperator(businessRule, conditions);
            } else {
                conditions = handleOtherOperator(businessRule, language, conditions);
            }
        }
        conditions += " )";
        return conditions;
    }

    private String handleOtherOperator(BusinessRule businessRule, String language, String conditions) {
        if (businessRule.getAttribute2() != null) {
            if (hasDifferentTablesForAttributes(businessRule)) {
                conditions += " " + translator.getTranslationForElement("Variable",language);
            } else {
                conditions += " :new." + businessRule.getAttribute2().getKolom();
            }
        } else if (businessRule.getValue1() != null) {
            conditions += " '" + businessRule.getValue1().getValue() + "'";
        }
        return conditions;
    }

    private String handleInOrNotInOperator(BusinessRule businessRule, String conditions) {
        String[] tags = businessRule.getValue1().getValue().split(":");
        conditions += " (";
        conditions += "'" + tags[0] + "'";
        for (int i = 1; i < tags.length; i++) {
            conditions = addTagToConditions(conditions, tags, i);
        }
        conditions += " )";
        return conditions;
    }

    private String addTagToConditions(String conditions, String[] tags, int i) {
        conditions += ",'" + tags[i] + "'";
        return conditions;
    }

    private String handleBetweenOrNotBetweenOperator(BusinessRule businessRule, String language, String conditions) {
        if(checkOperatorName(businessRule, "Between")){
            conditions += addCondition(language, "GreaterThanOrEqualsTo");
            conditions += " " + businessRule.getValue1().getValue();
            conditions += addCondition(language, "And");
            conditions += " :new." + businessRule.getAttribute1().getKolom();
            conditions += addCondition(language, "LesserThanOrEqualsTo");
        } else {
            conditions += addCondition(language, "LesserThan");
            conditions += " " + businessRule.getValue1().getValue();
            conditions += addCondition(language, "Or");
            conditions += " :new." + businessRule.getAttribute1().getKolom();
            conditions += addCondition(language, "GreaterThan");
        }
        conditions += " " + businessRule.getValue2().getValue();
        return conditions;
    }

    private boolean checkOperatorName(BusinessRule businessRule, String value) {
        return businessRule.getOperator().getNaam().equals(value);
    }

    private String addCondition(String language, String greaterThanOrEqualsTo) {
        return " " + translator.getTranslationForElement(greaterThanOrEqualsTo, language);
    }

    private boolean isOperatorName(BusinessRule businessRule, String between, String notBetween) {
        return businessRule.getOperator().getNaam().equals(between) || businessRule.getOperator().getNaam().equals(notBetween);
    }

    //TODO implement method
    private String generateVariables(BusinessRule businessRule) {
        String variables = "";
        if(hasTwoAttributes(businessRule)){
            if(hasDifferentTablesForAttributes(businessRule) || hasDifferentColumnsForAttributes(businessRule)){
                variables += "v_temp " + businessRule.getAttribute1().getTabel() + "." + businessRule.getAttribute1().getKolom() + "%type;";
            }
        }
        return variables;
    }

    private boolean hasDifferentColumnsForAttributes(BusinessRule businessRule) {
        return !businessRule.getAttribute1().getKolom().equals(businessRule.getAttribute2().getKolom());
    }
}
