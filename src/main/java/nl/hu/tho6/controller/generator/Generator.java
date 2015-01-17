package nl.hu.tho6.controller.generator;

import nl.hu.tho6.domain.businessrule.BusinessRule;
import nl.hu.tho6.translator.Translator;
import org.stringtemplate.v4.ST;

/**
 * Created by Liam on 17-12-2014.
 */
public class Generator {
    private static Generator ourInstance = new Generator();
    private Translator translator;

    public static Generator getInstance() {
        return ourInstance;
    }

    public ST generate(String language, BusinessRule businessRule) {
        ST pseudo = convertToPseudoCode(businessRule); ST code = translator.translate(pseudo, language); return code;
    }

    private Generator() {
        translator = new Translator();
    }

    private ST convertToPseudoCode(BusinessRule businessRule) {
        ST pseudo = new ST("");
        //TODO implement conversion
        return pseudo;
    }
}
