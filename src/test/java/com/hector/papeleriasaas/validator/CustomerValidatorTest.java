package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.CustomerNotFoundException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
@Tag("exception")
class CustomerValidatorTest {

    @Test
    @DisplayName("Testear excepción con parámetro de búsqueda Null")
    void testNullSearchParam() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> CustomerValidator.searchParamValidation(null),
                () -> "La excepción lanzada no es la esperada");

        assertEquals("Los siguientes campos son requeridos: Cedula o Nombre del cliente.", ex.getMessage(), () -> "El mensaje no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear excepción lanzada con parámetro de búsqueda vacío")
    void testEmptySearchParam() {
        EmptyFieldException ex = assertThrows(
                EmptyFieldException.class,
                () -> CustomerValidator.searchParamValidation(""),
                () -> "La excepción lanzada no es la esperada");

        assertEquals("Los siguientes campos son requeridos: Cedula o Nombre del cliente.", ex.getMessage(), () -> "El mensaje no es el esperado");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
    }

    @Test
    @Tag("empty_exception")
    @DisplayName("Testear excepción lanzada al usar el place holder para la búsqueda")
    void testPlaceHolderSearchParam() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> CustomerValidator.searchParamValidation("Buscar Cliente"),
                () -> "La excepción lanzada no es la esperada");

        assertEquals("Por favor ingrese el nombre o cedula del cliente", ex.getMessage(), () -> "El mensaje no es el esperado");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hector Acevedo", "1066726477"})
    @Tag("empty_exception")
    @DisplayName("Testear la excepción lanzada cuando el optional es vacío")
    void testOptionalEmpty(String param) {
        CustomerNotFoundException ex = assertThrows(
                CustomerNotFoundException.class,
                () -> CustomerValidator.optionalEmptyValidation(Optional.empty(), param),
                () -> "La excepción lanzada no es la esperada");

        assertEquals("Cliente no encontrado con parámetro: '" + param + "'", ex.getMessage(), () -> "El mensaje no es el esperado");
        assertInstanceOf(DAOException.class, ex, "La excepción lanzada no hereda de DAOException");
    }

}