package nl.hu.tho6.translator.filesystem.types.impl;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.filesystem.types.FileSystem;
import nl.hu.tho6.utils.file.PathUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class XMLFileSystem implements FileSystem {

    private static final String FILE_TYPE = ".xml";

    public void writeToFile(Dictionary dictionary) {
        //file wegschrijven naar xml
        try {
            JAXBContext context = JAXBContext.newInstance(Dictionary.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(dictionary, new File(PathUtils.getDictionaryPathTomcat() + PathUtils.DICTIONARY_PREFIX + dictionary.getLanguage() + FILE_TYPE));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dictionary readFromFile(String language) {
        //file uitlezen vanuit xml
        try {
            JAXBContext context = JAXBContext.newInstance(Dictionary.class);
            Unmarshaller un = context.createUnmarshaller();
            return (Dictionary) un.unmarshal(new File(PathUtils.getDictionaryPathTomcat() + PathUtils.DICTIONARY_PREFIX + language + FILE_TYPE));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFile(Dictionary dictionary) {
        try {
            File dictionaryToDelete = new File(PathUtils.getDictionaryPathTomcat() + PathUtils.DICTIONARY_PREFIX + dictionary.getLanguage() + FILE_TYPE);
            dictionaryToDelete.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


