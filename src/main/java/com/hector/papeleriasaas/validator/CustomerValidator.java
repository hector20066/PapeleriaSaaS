package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOCustomer;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.Optional;

public final class CustomerValidator {

    public static void searchParamValidation(String customer) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(customer, "Buscar Cliente")) {
            throw new EmptyFieldException("cédula o nombre del cliente");
        }
    }

    public static void optionalEmptyValidation(Optional<DTOCustomer> dtoOptional) throws InvalidInputException {
        if (dtoOptional.isEmpty()) {
            throw new InvalidInputException("No se encontró ningún cliente con ese criterio de búsqueda.");
        }
    }

}
