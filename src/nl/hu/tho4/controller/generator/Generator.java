package nl.hu.tho4.controller.generator;

import nl.hu.tho4.domain.businessrule.BusinessRule;
import nl.hu.tho4.translator.Translator;
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

    public ST generate(String language, BusinessRule br) {
        ST result = new ST("");
        //TODO convert to pseudo
        return result;
    }

    private Generator() {
        translator = new Translator();
    }

    private ST convertToPseudoCode(String language, BusinessRule br) {
        ST pseudo = new ST("");
        //TODO implement conversion
        return pseudo;
    }
}
