package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.utils.stringtemplate.StringTemplate;

import java.util.Map;

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
    public String generate(String language, BusinessRule businessRule) {
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
        }

        return commandExecuted;
    }

    private String generateTriggerName(BusinessRule businessRule) {
        String code = "BRG_TARGET_" + businessRule.getAttribute1().getTabel() + "_TRIGGER" + businessRule.getRuleID();
        return code;
    }

    private String generateTimpeOperator(BusinessRule businessRule) {
        String timeOperator = "update or insert or delete";
        return timeOperator;
    }

    private String generateTableName(BusinessRule businessRule) {
        String tableName = businessRule.getAttribute1().getTabel();
        return tableName;
    }

    private String generateError(BusinessRule businessRule, String language) {
        String error = "";
        if(businessRule.getErrorType().equals("CustomError")){
            error += translator.getTranslationForElement("ErrorFunction",language);
        } else {
            error += translator.getTranslationForElement("DefaultError",language);
        }
        return error;
    }

    private String getErrorMessage(BusinessRule businessRule){
        return businessRule.getError();
    }

    private String generateConditions(BusinessRule businessRule, String language) {
        String conditions = "( new." + businessRule.getAttribute1().getKolom();
        if(businessRule.getOperator().getNaam().equals("Between") || businessRule.getOperator().getNaam().equals("NotBetween")){
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("GreaterThanOrEqualTo",language);
            } else {
                conditions += " " + translator.getTranslationForElement("LesserThan",language);
            }
            conditions += " " + businessRule.getValue1().getValue();
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("And",language);
            } else {
                conditions += " " + translator.getTranslationForElement("Or",language);
            }
            conditions += " new." + businessRule.getAttribute1().getKolom();
            if(businessRule.getOperator().getNaam().equals("Between")){
                conditions += " " + translator.getTranslationForElement("LesserThanOrEqualTo0",language);
            } else {
                conditions += "( " + translator.getTranslationForElement("GreaterThan",language);
            }
            conditions += " " + businessRule.getValue2().getValue();
        } else {
            conditions += " " + translator.getTranslationForElement(businessRule.getOperator().getNaam(), language);
            if(businessRule.getAttribute2() != null){
                conditions += " new." + businessRule.getAttribute2().getKolom();
            } else if(businessRule.getValue1() != null){
                conditions += " " + businessRule.getValue1().getValue();
            }
        }
        conditions += " )";
        return conditions;
    }

    //TODO implement method
    private String generateVariables(BusinessRule businessRule) {
        String variables = "";
        return variables;
    }
}
