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
        //checken of dictionary param null is
        if (!(dictionary == null)) {
            dictionaries.add(dictionary);
        }
    }

    //TODO Subject to change
    public ST translate(ST st, String language) {
        //aan het begin een dictionary selecteren om te zorgen dat er niet voor elk element opnieuw de dictionary laden
        selectDictionary(language.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", ""));

        //string maken van de ST pseudoCode
        String pseudoCodeString = st.render();
        //pseudocodestring opdelen in elementen
        String[] elements = pseudoCodeString.split(" ");

        String translationString = "";

        //voor alle elementen translation  ophalen
        for (String element : elements) {
            translationString = translationString + getTranslationForElement(element);
        }

        //omzetten naar ST object
        ST translation = new ST(translationString);

        //dictionary weer op null zetten
        deSelectDictionary();
        return translation;
    }

    private void selectDictionary(String language) {
        //loop door lijst met dictionaries
        for (Dictionary dictionary : dictionaries) {
            //als talen gelijk zijn set current dictionary
            if (dictionary.getLanguage().equals(language)) {
                currentDictionary = dictionary;
            }
        }
    }

    private void deSelectDictionary() {
        //set current dictionary op null
        currentDictionary = null;
    }

    private String getTranslationForElement(String element) {
        String translatedElement = "";
        try {
            //translation ophalen van het element
            translatedElement = currentDictionary.getTranslationStringForElement(element);
        } catch (TranslationNotFoundException e) {
            e.printStackTrace();
        }
        return translatedElement;
    }
}