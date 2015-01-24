package nl.hu.tho6.translator.filesystem.types;

import nl.hu.tho6.translator.dictionary.Dictionary;

/**
 * Created by Liam on 16-1-2015.
 */
public interface FileSystem {

    public abstract void writeToFile(Dictionary dictionary);

    public abstract Dictionary readFromFile(String language);

    public abstract void deleteFile(Dictionary dictionary);
}
