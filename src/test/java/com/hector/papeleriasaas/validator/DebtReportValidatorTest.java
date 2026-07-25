package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.DebtNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
@Tag("exception")
class DebtReportValidatorTest {

    @Test
    @DisplayName("Testear la excepción lanzada con parámetros negativos")
    void testRowSelected() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.rowSelectedValidation(-1),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Seleccione la deuda para realizar el abono.", ex.getMessage());
    }

    @Test
    @DisplayName("Testear la excepción lanzada para deudas pagas")
    void testCompareStatus() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.compareStatusValidation(PaymentStatus.PAID),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Esta deuda ya está pagada.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    /* @ParameterizedTest
    @CsvSource({"0, 0", "-1000.00, 800.00", "-20000.00, 15000.00"})
    @DisplayName("Testear abono menor que 0")
    void testPricePassValue(BigDecimal pass, BigDecimal debt) {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.priceValueValidation(pass, debt),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El abono debe ser mayor que cero.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @ParameterizedTest
    @CsvSource({"1000.00, 0", "1000.00, 999.99", "23000.00, 20000.00"})
    @DisplayName("Testear abono mayor que la deuda")
    void testPriceDebtValue(BigDecimal pass, BigDecimal debt) {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.priceValueValidation(pass, debt),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El abono no puede ser mayor que la deuda actual.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }*/

    @Test
    @DisplayName("Testear parámetro de búsqueda null")
    void testSearchParamNull() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.searchParamValidation(null),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un nombre de cliente válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear parámetro de búsqueda vacío")
    void testSearchParamEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.searchParamValidation(""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un nombre de cliente válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear parámetro de búsqueda con texto por defecto")
    void testSearchParamPlaceHolder() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> DebtReportValidator.searchParamValidation("Buscar Cliente"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un nombre de cliente válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    /* @Test
    @Tag("empty_exception")
    @DisplayName("Testear lista de deudas vacía")
    void testDebtListEmpty() {
        String param = "Hector Acevedo";

        DebtNotFoundException ex = assertThrows(
                DebtNotFoundException.class,
                () -> DebtReportValidator.debtListEmptyValidation(Collections.emptyList(), param),
                () -> "La excepción lanzada no es la esperada."
        );

        assertEquals("No se han encontrado deudas para el parámetro de búsqueda: '" +param + "'", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
    } */

}
