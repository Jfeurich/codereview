package nl.hu.tho6.utils.file;

import nl.hu.tho6.utils.properties.GetPropertyValues;

import java.io.IOException;

/**
 * Created by Liam on 16-1-2015.
 */
public class PathUtils {
    public static final String DICTIONARY_PREFIX = "dictionary-";

    public static String getDictionaryPathTomcat() throws IOException {
        GetPropertyValues propertyValues = new GetPropertyValues();
        return propertyValues.getPropValue("tomcatFilePathDictionaries");
    }
}
