package nl.hu.tho6.translator.dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 16-1-2015.
 */
public class Dictionary {
    private String            language;
    private List<Translation> translations;

    public Dictionary(String language) {
        setLanguage(language); translations = new ArrayList<Translation>();
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void addTranslation(Translation translation) {
        translations.add(translation);
    }
}
