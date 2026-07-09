package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.CustomerNotFoundException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOCustomer;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class CustomerValidator {

    public static void searchParamValidation(String customer) throws InvalidInputException {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("Cedula o Nombre del cliente", customer);

        List<String> emptyFields = ValidationUtils.getEmptyFields(fields);

        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldException(emptyFields);
        }
        
        if (ValidationUtils.isInvalidInput(customer, "Buscar Cliente")) {
            throw new InvalidInputException("Por favor ingrese el nombre o cedula del cliente");
        }
    }

    public static void optionalEmptyValidation(Optional<DTOCustomer> dtoOptional, String param) throws DAOException {
        if (dtoOptional.isEmpty()) {
            throw new CustomerNotFoundException(param);
        }
    }

}
