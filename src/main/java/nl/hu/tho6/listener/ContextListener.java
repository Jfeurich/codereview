package nl.hu.tho6.listener;

import nl.hu.tho6.translator.filesystem.types.FileSystem;
import nl.hu.tho6.utils.file.PathUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * Created by Liam on 20-1-2015.
 */
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void readAllDictionaries(FileSystem fileSystem) {
        File dir = new File(PathUtils.DICTIONARY_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                //TODO zet alle dictionaries in translator met Translator.addDictionary(dictionary)
            }
        }
    }
}
