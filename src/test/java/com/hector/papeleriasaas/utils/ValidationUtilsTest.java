package com.hector.papeleriasaas.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    @DisplayName("Testear valores numéricos")
    void testIsNumeric() {
        assertAll(
                () -> assertFalse(ValidationUtils.isNumeric(null), () -> "El valor es null"),
                () -> assertFalse(ValidationUtils.isNumeric(""), () -> "El valor esta vacío"),
                () -> assertTrue(ValidationUtils.isNumeric("25000"), () -> "El valor no es numérico"),
                () -> assertFalse(ValidationUtils.isNumeric("2500O"), () -> "El valor contiene carácteres no numéricos"),
                () -> assertFalse(ValidationUtils.isNumeric("25000.00"), () -> "El valor con punto es numérico")
        );
    }

    @Test
    @DisplayName("Testear campos vacíos")
    void testIsEmptyFields() {
        assertAll(
                () -> assertTrue(ValidationUtils.isEmptyFields(null, null, "Hector Acevedo"), () -> "Habían valores nulos"),
                () -> assertTrue(ValidationUtils.isEmptyFields("", "", "Hector Acevedo"), () -> "Habían valores vacíos"),
                () -> assertFalse(ValidationUtils.isEmptyFields("Hector Acevedo", "1066726477"), () -> "Ha ocurrido un error en la validación")
        );
    }

    @Test
    @DisplayName("Testear valores inválidos")
    void testIsInvalidInput() {
        assertAll(
                () -> assertTrue(ValidationUtils.isInvalidInput("Buscar Cliente", "Buscar Cliente", "Nombre del Cliente"), () -> "El valor es diferente a los placeholders"),
                () -> assertTrue(ValidationUtils.isInvalidInput("", "Buscar Cliente", "Nombre del Cliente", "Cliente"), () -> "El valor está vacío"),
                () -> assertTrue(ValidationUtils.isInvalidInput(null, "Buscar Producto", "Producto"), () -> "El valor es null"),
                () -> assertFalse(ValidationUtils.isInvalidInput("Hector Acevedo", "Buscar Cliente", "Nombre del Cliente", "Cliente", "John Doe"))
        );
    }

    @Test
    @DisplayName("Testear obtención de campos vacíos")
    void testGetEmptyFields() {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("Cedula del Cliente", "");
        fields.put("Producto", null);
        fields.put("Proveedor", "Norma Corporation");
        fields.put("ID Cliente", "1066726477");
        fields.put("Dirección", "");

        List<String> listEmptyFields = ValidationUtils.getEmptyFields(fields);
        String emptyFieldsString = String.join(", ", listEmptyFields) + ".";

        assertFalse(listEmptyFields.isEmpty(), () -> "La lista esta vacía");
        assertEquals("Cedula del Cliente, Producto, Dirección.", emptyFieldsString, () -> "Los campos vacíos no son los esperados");
    }

}