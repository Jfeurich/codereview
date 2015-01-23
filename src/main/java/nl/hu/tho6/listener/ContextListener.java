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

    public ContextListener() {

    }

    public void contextInitialized(ServletContextEvent sce) {
        FileSystemFacade facade = new FileSystemFacade(new XMLFileSystem());
        try {
            readAllDictionaries(facade);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        FileSystemFacade facade = new FileSystemFacade(new XMLFileSystem());
        try {
            writeAllDictionaries(facade);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readAllDictionaries(FileSystemFacade facade) throws IOException {
        File dir = new File(PathUtils.getDictionaryPathTomcat());
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                String language = file.getName().replaceAll("dictionary-", "").replaceAll(".xml", "");

                Dictionary dic = null;
                try {
                    dic = facade.readDictionary(language);
                    dic.addObserver(facade);
                    System.out.println(dic);
                } catch (DictionaryNotFoundException e) {
                    e.printStackTrace();
                }
                Translator.addDictionary(dic);
            }
        }
    }

    private void writeAllDictionaries(FileSystemFacade facade) throws IOException{
        Translator translator = Translator.getInstance();
        for(Dictionary dic: translator.getAllDictionaries()){
            facade.writeDictionary(dic);
        }
    }
}
