package com.hector.papeleriasaas.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream file = AppConfig.class.getClassLoader().getResourceAsStream("config/application.properties")) {
            if (file == null) {
                throw new IllegalStateException("Archivo de configuración no encontrado");
            }

            properties.load(file);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Error al cargar configuración de la aplicación: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
