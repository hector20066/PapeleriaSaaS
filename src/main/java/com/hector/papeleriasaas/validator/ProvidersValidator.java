package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.SupplierNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOProvider;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.nio.file.ProviderNotFoundException;
import java.util.Optional;

public final class ProvidersValidator {

    public static void searchParamValidation(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar proveedor")) {
            throw new InvalidInputException("Por favor ingrese un término de búsqueda válido.");
        }
    }

    public static void providerEmptyValidation(Optional<DTOProvider> supplier, String param) throws DAOException {
        if (supplier.isEmpty()) {
            throw new SupplierNotFoundException(param);
        }
    }

}
