package nl.hu.tho6.utils.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Liam on 23-1-2015.
 */
public class GetPropertyValues {
    public String getPropValue(String prop) throws IOException{
        String result = "";

        Properties props = new Properties();
        String propFileName = "config.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null){
            props.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file "+ propFileName + " not found");
        }

        result = props.getProperty(prop);

        return result;
    }
}
