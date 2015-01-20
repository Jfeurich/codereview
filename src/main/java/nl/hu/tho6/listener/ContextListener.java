package nl.hu.tho6.listener;

import nl.hu.tho6.translator.Translator;
import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.FileSystemFacade;
import nl.hu.tho6.translator.filesystem.types.FileSystem;
import nl.hu.tho6.translator.filesystem.types.impl.XMLFileSystem;
import nl.hu.tho6.utils.file.PathUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import java.util.logging.FileHandler;

/**
 * Created by Liam on 20-1-2015.
 */
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
//        try{
//            ServletContext sc = sce.getServletContext();
//            synchronized (sc){
//                FileHandler fh = new FileHandler("locatie_bestand_hier");
//                fh.setFormatter();
//            }
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void readAllDictionaries(FileSystem fileSystem) {
        File dir = new File(PathUtils.DICTIONARY_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                //TODO zet alle dictionaries in translator met Translator.addDictionary(dictionary)
                //create dictionary object and fs.readfromfile
                //schrijven via filesystemfacade
                //observer filesystemfacade toevoegen aan dictionary
                FileSystemFacade facade = new FileSystemFacade(fileSystem);
                String s = file.getName();
                s.replaceAll("dictionary-","");
                Dictionary dic = null;
                try {
                    dic = facade.readDictionary(s);
                    dic.addObserver(facade);
                } catch (DictionaryNotFoundException e) {
                    e.printStackTrace();
                }
                Translator.addDictionary(dic);
            }
        }
    }
}
