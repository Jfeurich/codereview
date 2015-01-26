package nl.hu.tho6.translator.filesystem.types;

import nl.hu.tho6.translator.dictionary.Dictionary;

/**
 * Created by Liam on 16-1-2015.
 */
public interface FileSystem {

    public void writeToFile(Dictionary dictionary);

    public Dictionary readFromFile(String language);

    public void deleteFile(Dictionary dictionary);
}
