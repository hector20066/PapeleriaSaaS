package com.hector.papeleriasaas.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public static List<String> getEmptyFields(Map<String, String> fields) {

        List<String> emptyFields = new ArrayList<>();

        for (Map.Entry<String, String> field : fields.entrySet()) {
            String value = field.getValue();

            if (value == null || value.trim().isEmpty()) {
                emptyFields.add(field.getKey());
            }
        }

        return emptyFields;
    }

}
