package nl.hu.tho6.listener;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import nl.hu.tho6.utils.file.PathUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Liam on 20-1-2015.
 */
public class ContextListener implements ServletContextListener {

    public ContextListener(){

    }

    public void contextInitialized(ServletContextEvent sce) {
        FileSystemFacade facade = new FileSystemFacade(new XMLFileSystem());
        try {
            readAllDictionaries(facade);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Dictionary dic = new Dictionary();
//        dic.setLanguage("TEST");
//
//        facade.writeDictionary(dic);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void readAllDictionaries(FileSystemFacade facade) throws IOException {
        File dir = new File(PathUtils.getDictionaryPathTomcat());
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                String language = file.getName().replaceAll("dictionary-", "");

                Dictionary dic = null;
                try {
                    dic = facade.readDictionary(language);
                    dic.addObserver(facade);
                } catch (DictionaryNotFoundException e) {
                    e.printStackTrace();
                }

                Translator.addDictionary(dic);
            }
        }
    }
}
