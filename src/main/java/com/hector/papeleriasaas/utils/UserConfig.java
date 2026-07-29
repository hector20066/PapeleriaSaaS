package com.hector.papeleriasaas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserConfig {

    private static final Properties properties = new Properties();

    private static final Path CONFIG_DIR = Paths.get(System.getProperty("user.home"), ".papeleríaSaaS");
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("application.properties");

    static {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la configuración del usuario");
        }
    }

    private static void init() throws IOException {
        if (Files.notExists(CONFIG_DIR)) {
            Files.createDirectories(CONFIG_DIR);
        }

        if (Files.notExists(CONFIG_FILE)) {
            properties.setProperty("remember.token", "");
            properties.setProperty("remember.enabled", "false");

            try (OutputStream outputStream = Files.newOutputStream(CONFIG_FILE)) {
                properties.store(outputStream, "Configuración del usuario");
            }

            return;
        }

        try (InputStream inputStream = Files.newInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);

        try (OutputStream outputStream = Files.newOutputStream(CONFIG_FILE)) {
            properties.store(outputStream, "Configuración actualizada");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la configuración del usuario");
        }
    }

}
