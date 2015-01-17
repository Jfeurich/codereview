package nl.hu.tho6.translator;

import org.stringtemplate.v4.ST;

public class Translator {
    public Translator() {

    }

    public ST translate(ST st, String language) {
        ST translation = new ST("");
        //TODO implement translation of pseudoCode to real code
        return translation;
    }

    private ST getTranslationForElement(String element, String language) {
        ST translatedElement = new ST("");
        //TODO implement translation of pseudoCode element to real code element
        return translatedElement;
    }
}