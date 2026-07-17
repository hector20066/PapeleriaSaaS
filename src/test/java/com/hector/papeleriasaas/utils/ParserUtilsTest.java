package com.hector.papeleriasaas.utils;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParserUtilsTest {

    @ParameterizedTest
    @CsvSource({"25099.99, Total Venta", "0.00, Total Compra", "32992.21, Total Venta"})
    @DisplayName("Testear conversión al convertir a BigDecimal")
    void testParseBigDecimal(String value, String fieldName) throws InvalidInputException {
        BigDecimal parseValue = ParserUtils.parseBigDecimal(value, fieldName);

        assertNotNull(parseValue, () -> "El valor parseado es null");
        assertEquals(new BigDecimal(value), parseValue, () -> "El valor devuelto no es el esperado");
        assertDoesNotThrow(
                () -> ParserUtils.parseBigDecimal(value, fieldName),
                () -> "Se ha lanzado la excepcion"
        );
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al convertir a BigDecimal")
    void testParseBigDecimalThrows() {
        InvalidNumberInputException ex = assertThrows(
                InvalidNumberInputException.class,
                () -> ParserUtils.parseBigDecimal("25099.99s", "Total Venta"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El campo 'Total Venta' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertEquals(NumberFormatException.class, ex.getCause().getClass(), () -> "La causa de la excepción no es la esperada");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @ParameterizedTest
    @CsvSource({"25099, Total Venta", "0, Total Compra", "32992, Total Venta"})
    @DisplayName("Testear conversion a entero")
    void testParseInteger(String value, String fieldName) throws InvalidInputException {
        Integer parseValue = ParserUtils.parseInteger(value, fieldName);

        assertNotNull(parseValue, () -> "El valor parseado es null");
        assertEquals(Integer.parseInt(value), parseValue);
        assertDoesNotThrow(
                () -> ParserUtils.parseInteger(value, fieldName),
                () -> "Se ha lanzado la excepción"
        );
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al convertir a entero")
    void testParseIntegerThrows() {
        InvalidNumberInputException ex = assertThrows(
                InvalidNumberInputException.class,
                () -> ParserUtils.parseInteger("25009.99", "Total Venta"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El campo 'Total Venta' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertEquals(NumberFormatException.class, ex.getCause().getClass(), () -> "La causa de la excepción no es la esperada");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

}