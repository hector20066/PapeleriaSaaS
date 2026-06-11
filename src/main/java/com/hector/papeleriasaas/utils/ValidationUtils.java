package com.hector.papeleriasaas.utils;

import java.util.Arrays;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ValidationUtils {

    public static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.matches("\\d+");
    }

    public static boolean isEmptyFields(String... fields) {
        return Arrays.stream(fields).anyMatch(field -> field == null || field.isBlank());
    }

    public static boolean isInvalidInput(String value, String... placeholders) {
        if (value == null || value.isBlank()) {
            return true;
        }

        return Arrays.asList(placeholders).contains(value);
    }

}
