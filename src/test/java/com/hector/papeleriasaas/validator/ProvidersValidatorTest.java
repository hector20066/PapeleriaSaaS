package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.notfound.SupplierNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("exception")
class ProvidersValidatorTest {

    @Test
    @DisplayName("Testear el parámetro de búsqueda vacío")
    @Tag("empty_exception")
    void testSearchParamEmpty() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> {
                    ProvidersValidator.searchParamValidation("");
                    ProvidersValidator.searchParamValidation("Buscar proveedor");
                    ProvidersValidator.searchParamValidation(null);
                },
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    @DisplayName("Testear el parámetro de búsqueda con texto por defecto")
    void testSearchParamDefault() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ProvidersValidator.searchParamValidation("Buscar proveedor"),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    @DisplayName("Testear el parámetro de búsqueda null")
    void testSearchParamNull() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ProvidersValidator.searchParamValidation(null),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Por favor ingrese un término de búsqueda válido.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    @DisplayName("Testear el Optional vacío con el nombre como parámetro de búsqueda")
    void testProviderName() {
        String param = "Hector Acevedo";

        SupplierNotFoundException ex = assertThrows(
                SupplierNotFoundException.class,
                () -> ProvidersValidator.providerEmptyValidation(Optional.empty(), param),
                () -> "La excepcion lanzada no es la esperada"
        );

        assertEquals("Proveedor no encontrado con parámetro: '" + param + "'", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado");
    }

    @Test
    @DisplayName("Testear el Optional vacío con el Id como parámetro de búsqueda")
    void testProviderId() {
        String param = "1066726477";

        SupplierNotFoundException ex = assertThrows(
                SupplierNotFoundException.class,
                () -> ProvidersValidator.providerEmptyValidation(Optional.empty(), param),
                () -> "La excepcion lanzada no es la esperada"
        );

        assertEquals("Proveedor no encontrado con parámetro: '" + param + "'", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado");
    }

}