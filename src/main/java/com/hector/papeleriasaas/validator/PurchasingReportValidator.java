package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.PurchaseDetailsNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.List;

public final class PurchasingReportValidator {

    public static void searchParamValidator(String param) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(param, "Buscar Compra")) {
            throw new InvalidInputException("Por favor ingrese un número de compra válido.");
        }

        if (!ValidationUtils.isNumeric(param)) {
            throw new InvalidNumberInputException("número de compra");
        }
    }

    public static void listEmptyValidation(List<DTOPurchaseDetails> list) throws DAOException {
        if (list.isEmpty()) {
            throw new PurchaseDetailsNotFoundException("No se encontró ninguna compra con ese número.");
        }
    }

}
