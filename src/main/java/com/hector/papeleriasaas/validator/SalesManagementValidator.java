package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.math.BigDecimal;
import java.util.Optional;

public final class SalesManagementValidator {

    public static void validationsDebts(String customerId, String customerName) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(customerId, "Cedula")) {
            throw new EmptyFieldException("cédula del cliente (requerida para ventas con deuda)");
        }

        if (!ValidationUtils.isNumeric(customerId)) {
            throw new InvalidNumberInputException("cédula del cliente");
        }

        if (ValidationUtils.isInvalidInput(customerName, "Nombre")) {
            throw new EmptyFieldException("nombre del cliente (requerido para ventas con deuda)");
        }
    }

    public static void amountPaidEmptyValidation(String amountPaid) throws InvalidInputException {
        if (ValidationUtils.isEmptyFields(amountPaid)) {
            throw new EmptyFieldException("valor pagado");
        }
    }

    public static void amountPaidValueValidation(BigDecimal amountPaidValue, BigDecimal totalSaleValue) throws InvalidInputException {
        if (amountPaidValue.signum() < 0) {
            throw new InvalidInputException("El valor pagado no puede ser negativo.");
        }

        if (totalSaleValue.signum() <= 0) {
            throw new InvalidInputException("El total de la venta no puede ser menor o igual a 0");
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
