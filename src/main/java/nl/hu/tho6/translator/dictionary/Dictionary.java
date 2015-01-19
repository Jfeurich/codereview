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
        this.language = language.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}]+", "");
    }

    public String getLanguage() {
        return language;
    }

    public void addElementTranslation(Translation translation) {
        if ((translation != null)) {
            if (translation.getLanguage().equals(language)) {
                translation.addObserver(this);
                translations.add(translation);
                notifyObersvers(this);
            }
        }
    }

    public String getTranslationStringForElement(String element) throws TranslationNotFoundException {
        return getTranslationForElement(element).getElementTranslation();
    }

    public Translation getTranslationForElement(String element) throws TranslationNotFoundException {
        Translation foundTranslation = null;
        for (Translation t : getTranslations()) {
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
