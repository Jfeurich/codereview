package nl.hu.tho6.translator;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.TranslationNotFoundException;

import java.util.ArrayList;

public class Translator {
    private static Translator ourInstance = new Translator();
    private static ArrayList<Dictionary> dictionaries;

    public static Translator getInstance() {
        return ourInstance;
    }

    private Translator() {
        dictionaries = new ArrayList<Dictionary>();
    }

    public static void addDictionary(Dictionary dictionary) {
        //checken of dictionary param null is
        if (!(dictionary == null)) {
            if (!dictionaryExistsForLanguage(dictionary.getLanguage()))
                dictionaries.add(dictionary);
        }
    }

    public String getTranslationForElement(String element, String language) {
        Dictionary currentDictionary = selectDictionary(language);
        String translatedElement = "";

        try {
            //translation ophalen van het element
            translatedElement = currentDictionary.getTranslationStringForElement(element);
        } catch (TranslationNotFoundException e) {
            e.printStackTrace();
        }

        return translatedElement;
    }

    public Dictionary selectDictionary(String language) {
        Dictionary currentDictionary = null;
        //loop door lijst met dictionaries
        for (Dictionary dictionary : dictionaries) {
            //als talen gelijk zijn set current dictionary
            if (dictionary.getLanguage().equals(language)) {
                currentDictionary = dictionary;
            }
        }
        return currentDictionary;
    }

    private static boolean dictionaryExistsForLanguage(String language) {
        boolean dictionaryExists = false;

        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getLanguage().equals(language)) {
                dictionaryExists = true;
            }
        }

        return dictionaryExists;
    }

    public void removeDictionary(String language) {
        Dictionary d = null;
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getLanguage().equals(language)) {
                d = dictionary;
            }
        }
        if(d != null) {
            dictionaries.remove(d);
        }
    }

    public ArrayList<Dictionary> getDictionaries() {
        return dictionaries;
    }
}