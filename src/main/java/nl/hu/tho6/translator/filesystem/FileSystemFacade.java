package nl.hu.tho6.translator.filesystem;

import nl.hu.tho6.translator.dictionary.Dictionary;
import nl.hu.tho6.translator.dictionary.Translation;
import nl.hu.tho6.translator.dictionary.exception.DictionaryNotFoundException;
import nl.hu.tho6.translator.filesystem.types.FileSystem;
import nl.hu.tho6.utils.observer.Observer;

public class FileSystemFacade implements Observer {

    private FileSystem fileSystem;

    public FileSystemFacade(FileSystem fileSystem) {
        setFileSystem(fileSystem);
    }

    public void setFileSystem(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public void writeDictionary(Dictionary dictionary) {
        fileSystem.writeToFile(dictionary);
    }

    public Dictionary readDictionary(String language) throws DictionaryNotFoundException {
        Dictionary dictionary = fileSystem.readFromFile(language); if (!(dictionary == null)) {
            addObserverToTranslations(dictionary);
        } else {
            throw new DictionaryNotFoundException("The Dictionary for the language '" + language + "' is not found");
        }
        return dictionary;
    }

    private void addObserverToTranslations(Dictionary dictionary) {
        for (Translation t : dictionary.getTranslations()) {
            t.addObserver(dictionary);
        }
    }

    @Override
    public void update(Object observable) {
        writeDictionary((Dictionary) observable);
    }

}
