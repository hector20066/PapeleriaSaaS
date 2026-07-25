package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.PurchaseDetailsNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
@Tag("exception")
class PurchasingReportValidatorTest {

    @Test
    @DisplayName("Testear parámetro de búsqueda con valor por defecto")
    void testSearchParam() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> PurchasingReportValidator.searchParamValidator("Buscar Compra"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un número de compra válido.", ex.getMessage(), () -> "El mensaje de excepción lanzado no es el esperado");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear parámetro de búsqueda vacío")
    void testSearchParamEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> PurchasingReportValidator.searchParamValidator(""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un número de compra válido.", ex.getMessage(), () -> "El mensaje de excepción lanzado no es el esperado");
    }

    @Test
    @Tag("null_exception")
    @DisplayName("Testear parámetro de búsqueda null")
    void testSearchParamNull() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> PurchasingReportValidator.searchParamValidator(null),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un número de compra válido.", ex.getMessage(), () -> "El mensaje de excepción lanzado no es el esperado");
    }

    /* @Test
    @Tag("empty_exception")
    @DisplayName("Testear excepción lanzada con lista vacía")
    void testEmptyList() {
        PurchaseDetailsNotFoundException ex = assertThrows(
                PurchaseDetailsNotFoundException.class,
                () -> PurchasingReportValidator.listEmptyValidation(Collections.emptyList(), "1234"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Detalles de compra no encontrados con parámetro: '1234'", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción no hereda de DAOException");
    } */

}