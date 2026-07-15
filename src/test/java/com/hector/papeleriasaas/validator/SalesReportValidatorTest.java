package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.dao.notfound.SaleDetailsNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SalesReportValidatorTest {

    @Test
    void testSearchEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesReportValidator.searchValidation("Buscar Venta"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un número de venta válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    void testSearchInvalidNumber() {
        InvalidNumberInputException ex = assertThrows(
                InvalidNumberInputException.class,
                () -> SalesReportValidator.searchValidation("1005S"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("El campo 'número de venta' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción no hereda de InvalidInputException");
    }

    @Test
    void testSearchNotThrows() {
        assertDoesNotThrow(
                () -> SalesReportValidator.searchValidation("32523"),
                () -> "Se ha lanzado la excepción"
        );
    }

    @Test
    void testListEmpty() {
        SaleDetailsNotFoundException ex = assertThrows(
                SaleDetailsNotFoundException.class,
                () -> SalesReportValidator.listEmptyValidation(Collections.emptyList(), "12432"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Detalles de venta no encontrados con parámetro: '12432'", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción no hereda de DAOException");
    }

    @Test
    void testListEmptyNotThrows() {
        assertDoesNotThrow(
                () -> SalesReportValidator.listEmptyValidation(List.of(new DTOSaleDetails.Builder().build()), "1242"),
                () -> "Se ha lanzado la excepción"
        );
    }

    @Test
    void testBestSellingEmpty() {
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> SalesReportValidator.bestSellingEmptyValidation(Optional.empty()),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Producto no encontrado", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
    }

}