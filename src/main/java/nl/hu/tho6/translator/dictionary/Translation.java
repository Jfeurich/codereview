package nl.hu.tho6.translator.dictionary;

import nl.hu.tho6.utils.observer.Observable;

import javax.xml.bind.annotation.*;

/**
 * Created by Liam on 16-1-2015.
 */
@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Translation extends Observable {
    @XmlAttribute
    private String language;
    @XmlAttribute
    private String element;
    @XmlElement (name = "elementTranslation")
    private String elementTranslation;

    public Translation() {

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language; notifyObersvers(this);
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element; notifyObersvers(this);
    }

    public String getElementTranslation() {
        return elementTranslation;
    }

    public void setElementTranslation(String elemntTranslation) {
        this.elementTranslation = elemntTranslation; notifyObersvers(this);
    }
}
