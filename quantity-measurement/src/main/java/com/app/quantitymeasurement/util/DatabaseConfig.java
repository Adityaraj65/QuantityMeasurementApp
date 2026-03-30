package com.app.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static DatabaseConfig instance;
    private final Properties props = new Properties();

    private DatabaseConfig() {
        load();
    }

    public static synchronized DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    private void load() {
        try (InputStream input =
                     getClass().getClassLoader().getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties not found");
            }

            props.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Config load failed", e);
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}