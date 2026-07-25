package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class SalesManagementValidator {

    public static void validationsDebts(String customerId, String customerName) throws InvalidInputException {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("ID Cliente", customerId);
        fields.put("Nombre del Cliente", customerName);

        List<String> emptyFields = ValidationUtils.getEmptyFields(fields);

        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldException(emptyFields);
        }

        if (ValidationUtils.isInvalidInput(customerId, "Cedula")) {
            throw new InvalidInputException("La cédula del cliente es requerida para ventas con deudas");
        }

        if (!ValidationUtils.isNumeric(customerId)) {
            throw new InvalidNumberInputException("cédula del cliente");
        }

        if (ValidationUtils.isInvalidInput(customerName, "Nombre")) {
            throw new InvalidInputException("El nombre del cliente es requerido para ventas con deudas");
        }
    }

    public static void amountPaidEmptyValidation(String amountPaid) throws InvalidInputException {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("Cantidad Pagada", amountPaid);

        List<String> emptyFields = ValidationUtils.getEmptyFields(fields);

        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldException(emptyFields);
        }
    }

    public static void searchInvalidParamValidation(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar Producto")) {
            throw new InvalidInputException("No se encontró el producto, por favor ingrese el nombre.");
        }
    }

    public static void emptyProductValidation(Optional<DTOProductDetails> product, String param) throws ProductNotFoundException {
        if (product.isEmpty()) {
            throw new ProductNotFoundException(param);
        }
    }

}
