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
        //filesysytem setten zodat de klasse weet welke hij moet gebruiken
        this.fileSystem = fileSystem;
    }

    public void writeDictionary(Dictionary dictionary) {
        //wegschrijven dictionary naar file
        fileSystem.writeToFile(dictionary);
    }

    public Dictionary readDictionary(String language) throws DictionaryNotFoundException {
        //dictionary lezen
        Dictionary dictionary = fileSystem.readFromFile(language);
        if (!(dictionary == null)) {
            //als de dictionary bestaat observer toevoegen aan de translations in de dictionary
            addObserverToTranslations(dictionary);
        } else {
            throw new DictionaryNotFoundException("The Dictionary for the language '" + language + "' is not found");
        }
        return dictionary;
    }

    public void deleteDictionary(Dictionary dictionary){
        fileSystem.deleteFile(dictionary);
    }

    private void addObserverToTranslations(Dictionary dictionary) {
        //loop door lijst met translations en de dictionary als observer toevoegen
        for (Translation t : dictionary.getTranslations()) {
            t.addObserver(dictionary);
        }
    }

    @Override
    public void update(Object observable) {
        writeDictionary((Dictionary) observable);
    }

}
