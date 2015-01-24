package nl.hu.tho6.translator.dictionary;

import nl.hu.tho6.translator.dictionary.exception.TranslationNotFoundException;
import nl.hu.tho6.utils.observer.Observable;
import nl.hu.tho6.utils.observer.Observer;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 16-1-2015.
 */
//TODO plaats vinden om dictionaries te storen
@XmlRootElement (name = "dictionary")
@XmlAccessorType (XmlAccessType.FIELD)
public class Dictionary extends Observable implements Observer {
    @XmlAttribute
    private String            language;
    @XmlElement (name = "translation")
    private List<Translation> translations;

    public Dictionary() {
        translations = new ArrayList<Translation>();
    }

    public void setLanguage(String language) {
        //taal van de dictionary setten, alles naar lowercase en alleen de letters over laten
        this.language = language.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", "");
    }

    public String getLanguage() {
        return language;
    }

    public void addElementTranslation(Translation translation) {
        //om nullpointers te voorkomen checken of translation param null is
        if ((translation != null)) {
            //translation alleen toevoegen als de taal van de translation gelijk is aan de van de dictionary
            if (translation.getLanguage().equals(language)) {
                    //deze dictionary toevoegen als observer
                    translation.addObserver(this);
                //translation aan dictionary toevoegen
                translations.add(translation);
                //observers notifyen (observer is een notifier)
                notifyObersvers(this);
            }
        }
    }

    public String getTranslationStringForElement(String element) throws TranslationNotFoundException {
        //translation ophalen die bij het element horen en omzetten naar een string
        return getTranslationForElement(element).getElementTranslation();
    }

    public Translation getTranslationForElement(String element) throws TranslationNotFoundException {
        Translation foundTranslation = null;
        //loop door lijst met translations
        for (Translation t : getTranslations()) {
            //als de elementen overeenkomen is hhet de juiste translation
            if (t.getElement().equals(element)) {
                foundTranslation = t;
            }
        }

        if (foundTranslation == null) {
            throw new TranslationNotFoundException("The translation for the element '" + element + "' cannot be found");
        }

        return foundTranslation;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    @Override
    public void update(Object observable) {
        notifyObersvers(this);
    }
}
