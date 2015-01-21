package nl.hu.tho6.utils.file;

import java.io.File;

/**
 * Created by Liam on 16-1-2015.
 */
public class PathUtils {
    public static final String DICTIONARY_PATH = getRelativePath() + "/res/dictionaries/";
    public static final String DICTIONARY_PREFIX = "dictionary-";

    public static String getRelativePath() {
        String filepath = new File("").getAbsolutePath();
        return filepath;
    }
}
