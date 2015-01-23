package nl.hu.tho6.translator.filesystem;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * FileSystemFacade Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>jan 23, 2015</pre>
 */
public class XMLFileSystemFacadeTest {
    private FileSystemFacade facade;

    @Before
    public void before() throws Exception {
        facade = new FileSystemFacade(new XMLFileSystem());
    }

    @After
    public void after() throws Exception {
        facade = null;
    }

    /**
     * Method: writeDictionary(Dictionary dictionary)
     */
    @Test
    public void testWriteDictionary() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setLanguage("test");
        facade.writeDictionary(dictionary);

        Dictionary dictionary1  = facade.readDictionary("test");

        assertEquals(dictionary.getLanguage(), dictionary1.getLanguage());
    }

}
