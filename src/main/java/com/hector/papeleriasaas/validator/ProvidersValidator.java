package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.utils.ValidationUtils;

public final class ProvidersValidator {

    public static void searchParamValidation(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar proveedor")) {
            throw new InvalidInputException("Por favor ingrese un término de búsqueda válido.");
        }
    }

}
