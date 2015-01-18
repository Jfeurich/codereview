package nl.hu.tho6.translator;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.dictionary.exception.TranslationNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;

public class Translator {
    private static Translator ourInstance = new Translator();
    private static List<Dictionary> dictionaries;
    private        Dictionary       currentDictionary;

    public static Translator getInstance() {
        return ourInstance;
    }

    private Translator() {
        dictionaries = new ArrayList<Dictionary>();
        currentDictionary = null;
        init();
    }

    //alleen zolang we nog geen listener hebben
    private void init() {
        FileSystemFacade fs = new FileSystemFacade(new XMLFileSystem());
        try {
            addDictionary(fs.readDictionary("plsql"));
        } catch (DictionaryNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addDictionary(Dictionary dictionary) {
        if (!(dictionary == null)) {
            dictionaries.add(dictionary);
        }
    }

    public ST translate(ST st, String language) {
        selectDictionary(language.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", ""));

        String pseudoCodeString = st.render();
        String[] elements = pseudoCodeString.split(" ");

        String translationString = "";

        for (String element : elements) {
            translationString = translationString + getTranslationForElement(element);
        }

        ST translation = new ST(translationString);

        deSelectDictionary();
        return translation;
    }

    private void selectDictionary(String language) {
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getLanguage().equals(language)) {
                currentDictionary = dictionary;
            }
        }
    }

    private void deSelectDictionary() {
        currentDictionary = null;
    }

    private String getTranslationForElement(String element) {
        String translatedElement = "";
        try {
            translatedElement = currentDictionary.getTranslationStringForElement(element);
        } catch (TranslationNotFoundException e) {
            e.printStackTrace();
        }
        return translatedElement;
    }
}