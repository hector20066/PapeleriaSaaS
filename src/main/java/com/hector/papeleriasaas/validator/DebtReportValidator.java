package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.utils.ValidationUtils;

public final class DebtReportValidator {

    public static void rowSelectedValidation(int row) throws InvalidInputException {
        if (row < 0) {
            throw new InvalidInputException("Seleccione la deuda para realizar el abono.");
        }
    }

    public static void compareStatusValidation(PaymentStatus status) throws InvalidInputException {
        if (status == PaymentStatus.PAID) {
            throw new InvalidInputException("Esta deuda ya está pagada.");
        }
    }

    public static void searchParamValidation(String customer) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(customer, "Buscar Cliente")) {
            throw new InvalidInputException("Por favor ingrese un nombre de cliente válido.");
        }
    }

}
