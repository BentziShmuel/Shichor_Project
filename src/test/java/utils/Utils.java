package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public String readProperty(String key) {
        String value="";
        try (InputStream input = new FileInputStream("./src/main/java/resources/data/configuration.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            value = prop.getProperty(key);

        } catch (Exception e) {

        }
        return value;
    }

}
