package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
@Tag("exception")
class InventoryValidatorTest {

    @Test
    @DisplayName("Testear selección de fila")
    void testRow() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> InventoryValidator.rowValidation(-1),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Seleccione el producto que desea modificar.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear parámetro de búsqueda null")
    void testSearchParamNull() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> InventoryValidator.searchParamValidation(null),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear parámetro de búsqueda vacío")
    void testSearchParamEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> InventoryValidator.searchParamValidation(""),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @Test
    @DisplayName("Testear parámetro de búsqueda con texto por defecto")
    void testSearchParamPlaceHolder() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> InventoryValidator.searchParamValidation("Buscar Producto"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado");
    }

    @ParameterizedTest
    @CsvSource({"Hector Acevedo", "1066726477"})
    @Tag("empty_exception")
    @DisplayName("Testar Optional de producto vacío")
    void testDtoProductEmpty(String param) {
        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> InventoryValidator.dtoProductEmptyValidation(Optional.empty(), param),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Producto no encontrado con parámetro: '" + param + "'", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
    }

}