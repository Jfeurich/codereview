package nl.hu.tho6.translator;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Translator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>jan 23, 2015</pre>
 */
public class TranslatorTest {
    private Translator translator;

    @Before
    public void before() throws Exception {
        //Dictionary inlezen
        FileSystemFacade facade = new FileSystemFacade();
        Dictionary dic = facade.readDictionary("plsql");
        Translator.addDictionary(dic);

        translator = Translator.getInstance();
    }

    @After
    public void after() throws Exception {
        translator.removeDictionary("plqsl");
        translator = null;
    }

    /**
     * Method: getInstance()
     */
    @Test
    public void testGetInstance() throws Exception {
        Translator translator1 = Translator.getInstance();
        assertEquals(translator, translator1);
    }

    /**
     * Method: addDictionary(Dictionary dictionary)
     */
    @Test
    public void testAddDictionary() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setLanguage("mysql");

        Translator.addDictionary(dictionary);

        assertEquals(dictionary, translator.selectDictionary("mysql"));
    }

    /**
     * Method: getTranslationForElement(String element, String language)
     */
    @Test
    public void testGetTranslationForElement() throws Exception {
        String translation = translator.getTranslationForElement("GreaterThan", "plsql");
        assertEquals(">", translation);
    }

    /**
     * Method: removeDictionary(String language)
     */
    @Test
    public void testRemoveDictionary() throws Exception {
        translator.removeDictionary("mysql");

        assertNull(translator.selectDictionary("mysql"));
    }
} 
