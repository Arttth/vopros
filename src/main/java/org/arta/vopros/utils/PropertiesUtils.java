package org.arta.vopros.utils;

import org.arta.vopros.exception.LoadingPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtils {
    private final static Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesUtils.class.getClassLoader().
                getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new LoadingPropertiesException(e);
        }
    }

    private PropertiesUtils() {}

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

}