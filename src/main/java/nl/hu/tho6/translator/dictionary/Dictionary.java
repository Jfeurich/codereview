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
@XmlRootElement (name = "dictionary")
@XmlAccessorType (XmlAccessType.FIELD)
public class Dictionary extends Observable implements Observer {
    @XmlTransient
    private String            language;
    @XmlElement (name = "translation")
    private List<Translation> translations;

    public Dictionary() {
        translations = new ArrayList<Translation>();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void addElementTranslation(Translation translation) {
        if (!(translation == null)) {
            translation.addObserver(this); translations.add(translation); notifyObersvers(this);
        }
    }

    public String getTranslationStringForElement(String element) throws TranslationNotFoundException {
        String foundTranslation = ""; for (Translation t : translations) {
            if (t.getElement().equals(element)) {
                foundTranslation = t.getElementTranslation();
            } else {
                throw new TranslationNotFoundException("The translation for the element '" + element + "' cannot be found in this dictionary");
            }
        } return foundTranslation;
    }

    public Translation getTranslationForElement(String element) throws TranslationNotFoundException {
        Translation foundTranslation = null; for (Translation t : translations) {
            if (t.getElement().equals(element)) {
                foundTranslation = t;
            } else {
                throw new TranslationNotFoundException("The translation for the element '" + element + "' cannot be found in this dictionary");
            }
        } return foundTranslation;
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    @Override
    public void update(Object observable) {
        notifyObersvers(this);
    }
}
