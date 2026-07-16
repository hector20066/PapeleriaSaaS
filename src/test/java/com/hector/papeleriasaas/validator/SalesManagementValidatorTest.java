package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
@Tag("exception")
class SalesManagementValidatorTest {

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear el ID del cliente vacío")
    void testValidationDebtsEmptyId() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> SalesManagementValidator.validationsDebts("", "Hector Acevedo"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los siguientes campos son requeridos: ID Cliente.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear nombre del cliente vacío")
    void testValidationDebtsEmptyName() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> SalesManagementValidator.validationsDebts("1066726477", ""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los siguientes campos son requeridos: Nombre del Cliente.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear campos del cliente vacío")
    void testValidationDebtsEmptyFields() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> SalesManagementValidator.validationsDebts("", ""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los siguientes campos son requeridos: ID Cliente, Nombre del Cliente.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @DisplayName("Testear valor por defecto en ID del cliente")
    void testInvalidInput() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.validationsDebts("Cedula", "Hector Acevedo"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("La cédula del cliente es requerida para ventas con deudas", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear ID del cliente con valor numérico")
    void testInvalidNumericId() {
        InvalidNumberInputException ex = assertThrows(
                InvalidNumberInputException.class,
                () -> SalesManagementValidator.validationsDebts("106672647T", "Hector Acevedo"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El campo 'cédula del cliente' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @DisplayName("Testear Nombre del cliente con valor por defecto")
    void testInvalidCustomerName() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.validationsDebts("1066726477", "Nombre"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El nombre del cliente es requerido para ventas con deudas", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear validaciones de los datos del cliente")
    void testInvalid() {
        assertDoesNotThrow(
                () -> SalesManagementValidator.validationsDebts("1066726477", "Hector Acevedo"),
                () -> "Se han lanzado excepciones"
        );
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear cantidad pagada vacía")
    void testAmountPaidEmpty() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> SalesManagementValidator.amountPaidEmptyValidation(""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los siguientes campos son requeridos: Cantidad Pagada.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @DisplayName("Testear validación de cantidad pagada")
    void testAmountPaid() {
        assertDoesNotThrow(
                () -> SalesManagementValidator.amountPaidEmptyValidation("25000.00"),
                () -> "Se ha lanzado la excepcion"
        );
    }

    @Test
    @DisplayName("Testear cantidad pagada negativa")
    void testAmountPaidValue() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.amountPaidValueValidation(new BigDecimal("-25000.00"), new BigDecimal("26000.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El valor pagado no puede ser negativo.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear total de la venta cero")
    void testTotalSaleValue() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.amountPaidValueValidation(new BigDecimal("25000.00"), BigDecimal.ZERO),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El total de la venta no puede ser menor o igual a 0", ex.getMessage(), () -> "El total de la venta no puede ser menor o igual a 0");
    }

    @Test
    @DisplayName("Testear validación de los campos de dinero")
    void testAmountPaidNotThrows() {
        assertDoesNotThrow(
                () -> SalesManagementValidator.amountPaidValueValidation(new BigDecimal("25000.00"), new BigDecimal("26000.00")),
                () -> "Se ha lanzado la excepción"
        );
    }

    @Test
    @DisplayName("Testear validación del parámetro de búsqueda")
    void testSearchParam() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.searchInvalidParamValidation("Buscar Producto"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("No se encontró el producto, por favor ingrese el nombre.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear parámetro de búsqueda vacío")
    void testSearchParamEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementValidator.searchInvalidParamValidation(""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("No se encontró el producto, por favor ingrese el nombre.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    void testSearchParamNotThrows() {
        assertDoesNotThrow(
                () -> SalesManagementValidator.searchInvalidParamValidation("Cuaderno Norma"),
                () -> "Se ha lanzado la excepcion"
        );
    }

    @Test
    @Tag("empty_exception")
    void testEmptyProduct() {
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> SalesManagementValidator.emptyProductValidation(Optional.empty(), "Cuaderno Norma"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Producto no encontrado con parámetro: 'Cuaderno Norma'", ex.getMessage(), () -> "El mensaje no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción no hereda de DAOException");
    }

}