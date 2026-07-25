package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.utils.ValidationUtils;

public final class SalesReportValidator {

    public static void searchValidation(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar Venta")) {
            throw new InvalidInputException("Por favor ingrese un número de venta válido.");
        }

        if (!ValidationUtils.isNumeric(param)) {
            throw new InvalidNumberInputException("número de venta");
        }
    }

}
