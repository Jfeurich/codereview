package nl.hu.tho6.translator.dictionary;

/**
 * Created by Liam on 16-1-2015.
 */
public class Translation {
    private String language;
    private String element;
    private String translation;

    public Translation(String language, String element, String translation) {
        setLanguage(language); setElement(element); setTranslation(translation);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
