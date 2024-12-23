package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class AppProperty {
    private static final String APP_PROPERTIES = "application.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream resourceAsStream = AppProperty.class.getClassLoader().getResourceAsStream(APP_PROPERTIES)) {
            properties.load(Objects.requireNonNull(resourceAsStream));
        } catch (IOException e) {
            System.err.println("Unable to load properties file: " + APP_PROPERTIES);
        }
    }

    public static String getProperty(String key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        if (key.isEmpty()) {
            throw new IllegalArgumentException("key is empty");
        }

        return properties.getProperty(key);
    }
}
