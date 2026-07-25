package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.utils.ValidationUtils;

public final class PurchasingReportValidator {

    public static void searchParamValidator(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar Compra")) {
            throw new InvalidInputException("Por favor ingrese un número de compra válido.");
        }

        if (!ValidationUtils.isNumeric(param)) {
            throw new InvalidNumberInputException("número de compra");
        }
    }

}
