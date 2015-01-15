package nl.hu.tho4.translator;

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

    //TODO assert if methods still necessary
    private String generateTriggerName() {
        String s = "applicatienaam_entiteit_typerestrictie_soortrule";
        return s;
    }

    private String generateTriggerCode() {
        String s = "de gegenereerde trigger code";
        return s;
    }

    private String generateExceptionCode() {
        String s = " de exceptie afhandeling";
        return s;
    }
}