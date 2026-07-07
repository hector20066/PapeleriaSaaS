package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.math.BigDecimal;

public final class ModifyProductValidator {

    public static void fieldsValidation(String id, String product, String amount, String purchasePrice, String salePrice) throws InvalidInputException {
        if (ValidationUtils.isEmptyFields(id, product, amount, purchasePrice, salePrice)) {
            throw new EmptyFieldException("datos del producto");
        }

        if (!ValidationUtils.isNumeric(amount) || !ValidationUtils.isNumeric(id)) {
            throw new InvalidNumberInputException("ID y cantidad");
        }
    }

    public static void productValidation(int productAmount, BigDecimal purchasePriceValue, BigDecimal salePriceValue) throws InvalidInputException {
        if (productAmount < 0) {
            throw new InvalidInputException("La cantidad disponible no puede ser negativa.");
        }

        if (purchasePriceValue.signum() <= 0 || salePriceValue.signum() <= 0) {
            throw new InvalidInputException("Los precios no pueden ser negativos.");
        }
    }

}
