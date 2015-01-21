package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import org.stringtemplate.v4.ST;

import java.util.Map;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    private Translator translator;
    private boolean commandExecuted;

    public static Generator getInstance() {
        return ourInstance;
    }

    //TODO refactor
    public String generate(String language, BusinessRule businessRule) {
        ST templateForLanguage = getTemplateForLanguage(language);

        boolean unfilledAttributes = true;
        Map<String, Object> attributes = templateForLanguage.getAttributes();

        while (unfilledAttributes) {
            for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
                if (attribute.getValue() == null) {
                    execute(attribute.getKey(), businessRule, language, templateForLanguage);
                    if (!commandExecuted) {
                        templateForLanguage.add(attribute.getKey(), translator.getTranslationForElement(attribute.getKey(), language));
                    }
                }
            }

            templateForLanguage = new ST(templateForLanguage.render());
            attributes = templateForLanguage.getAttributes();

            unfilledAttributes = (!(attributes.size() == 0));
        }

        return templateForLanguage.render();
    }

    private Generator() {
        translator = Translator.getInstance();
    }

    private ST getTemplateForLanguage(String language) {
        return new ST(translator.getTranslationForElement("Template", language));
    }

    private ST execute(String command, BusinessRule businessRule, String language, ST templateForLanguage) {
        commandExecuted = false;

        if (command.equals("TriggerName")) {
            templateForLanguage.add(command, generateTriggerName(businessRule));
            commandExecuted = true;
        } else if (command.equals("TimeOperator")) {
            templateForLanguage.add(command, generateTimpeOperator(businessRule));
            commandExecuted = true;
        } else if (command.equals("TableName")) {
            templateForLanguage.add(command, generateTableName(businessRule));
            commandExecuted = true;
        } else if (command.equals("Variables")) {
            templateForLanguage.add(command, generateVariables(businessRule));
            commandExecuted = true;
        } else if (command.equals("Conditions")) {
            templateForLanguage.add(command, generateConditions(businessRule));
            commandExecuted = true;
        } else if (command.equals("Error")) {
            templateForLanguage.add(command, generateError(businessRule));
            commandExecuted = true;
        }

        return templateForLanguage;
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

    //TODO implement method
    private String generateError(BusinessRule businessRule) {
        String error = "";
        return error;
    }

    //TODO implement method
    private String generateConditions(BusinessRule businessRule) {
        String conditions = "";
        return conditions;
    }

    //TODO implement method
    private String generateVariables(BusinessRule businessRule) {
        String variables = "";
        return variables;
    }
}
