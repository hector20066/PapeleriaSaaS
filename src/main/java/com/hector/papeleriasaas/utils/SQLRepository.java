package com.hector.papeleriasaas.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SQLRepository {

    private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

    private SQLRepository() {

    }

    public static String get(String path) {
        return CACHE.computeIfAbsent(path, SQLRepository::loadFile);
    }

    private static String loadFile(String path) {
        try (InputStream is = SQLRepository.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("SQL file not found: " + path);
            }

            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL file: " + path, e);
        }
    }

}
