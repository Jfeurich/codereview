package nl.hu.tho6.translator.dictionary;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 16-1-2015.
 */
@XmlRootElement (name = "dictionary")
@XmlAccessorType (XmlAccessType.FIELD)
public class Dictionary {
    @XmlTransient
    private String            language;
    @XmlElement (name = "translation")
    private List<Translation> translations;

    public Dictionary() {
        translations = new ArrayList<Translation>();
    }

    public String getLanguage() {
        return language;
    }

    public String getTranslationForElement(String element) {
        String foundTranslation = ""; for (Translation t : translations) {
            if (t.getElement().equals(element)) {
                foundTranslation = t.getElementTranslation();
            }
        } return foundTranslation;
    }
}
