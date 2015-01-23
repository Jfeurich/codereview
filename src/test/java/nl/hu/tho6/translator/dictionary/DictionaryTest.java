package nl.hu.tho6.translator.dictionary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Dictionary Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>jan 23, 2015</pre>
 */
public class DictionaryTest {
    private Dictionary dictionary;

    @Before
    public void before() throws Exception {
        dictionary = new Dictionary();
        dictionary.setLanguage("plqsl");
    }

    @After
    public void after() throws Exception {
        dictionary = null;
    }

    /**
     * Method: setLanguage(String language)
     */
    @Test
    public void testGetSetLanguage() throws Exception {
        assertEquals("plqsl", dictionary.getLanguage());
    }

    /**
     * Method: addElementTranslation(Translation translation)
     */
    @Test
    public void testAddElementTranslation() throws Exception {
        Translation translation = new Translation();
        translation.setLanguage("plqsl");
        translation.setElement("GreaterThan");
        translation.setElementTranslation(">");

        dictionary.addElementTranslation(translation);

        assertEquals(translation, dictionary.getTranslationForElement("GreaterThan"));
    }

    /**
     * Method: getTranslationStringForElement(String element)
     */
    @Test
    public void testGetTranslationStringForElement() throws Exception {
        Translation translation = new Translation();
        translation.setLanguage("plqsl");
        translation.setElement("GreaterThan");
        translation.setElementTranslation(">");

        dictionary.addElementTranslation(translation);

        assertEquals(translation.getElementTranslation(), dictionary.getTranslationStringForElement("GreaterThan"));
    }
} 
