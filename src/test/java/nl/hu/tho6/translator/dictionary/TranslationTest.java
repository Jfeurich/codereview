package nl.hu.tho6.translator.dictionary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Translation Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>jan 23, 2015</pre>
 */
public class TranslationTest {
    private Translation translation;

    @Before
    public void before() throws Exception {
        translation = new Translation();
    }

    @After
    public void after() throws Exception {
        translation = null;
    }

    /**
     * Method: getLanguage()
     */
    @Test
    public void testGetSetLanguage() throws Exception {
        translation.setLanguage("plsql");
        assertEquals("plsql", translation.getLanguage());
    }

    /**
     * Method: getElement()
     */
    @Test
    public void testGetSetElement() throws Exception {
        translation.setElement("GreaterThan");
        assertEquals("GreaterThan", translation.getElement());
    }

    /**
     * Method: getElementTranslation()
     */
    @Test
    public void testGetSetElementTranslation() throws Exception {
        translation.setElementTranslation(">");
        assertEquals(">", translation.getElementTranslation());
    }
} 
