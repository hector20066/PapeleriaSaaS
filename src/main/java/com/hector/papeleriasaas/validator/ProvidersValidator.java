package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOProvider;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.Optional;

public final class ProvidersValidator {

    public static void searchParamValidation(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar proveedor")) {
            throw new InvalidInputException("Por favor ingrese un término de búsqueda válido.");
        }
    }

    public static void providerEmptyValidation(Optional<DTOProvider> supplier) throws InvalidInputException {
        if (supplier.isEmpty()) {
            throw new InvalidInputException("No se encontró ningún proveedor con ese criterio de búsqueda.");
        }
    }

}
