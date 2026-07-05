package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.math.BigDecimal;
import java.util.List;

public final class PurchaseProductValidator {

    public static void registerPurchaseValidation(String nit, String rSocial, String address, String phone, String email) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(nit, "Nit")) {
            throw new EmptyFieldException("NIT del proveedor");
        }

        if (ValidationUtils.isInvalidInput(rSocial, "R. Social")) {
            throw new EmptyFieldException("razón social del proveedor");
        }

        if (ValidationUtils.isInvalidInput(address, "Direccion")) {
            throw new EmptyFieldException("dirección del proveedor");
        }

        if (ValidationUtils.isInvalidInput(phone, "Telefono")) {
            throw new EmptyFieldException("teléfono del proveedor");
        }

        if (ValidationUtils.isInvalidInput(email, "Correo")) {
            throw new EmptyFieldException("correo del proveedor");
        }

        if (!ValidationUtils.isNumeric(nit)) {
            throw new InvalidNumberInputException("NIT del proveedor");
        }
    }

    public static void addProductValidation(String product, String amount, String purchasePrice, String salePrice) throws InvalidInputException {
        if (ValidationUtils.isEmptyFields(product, amount, purchasePrice, salePrice)) {
            throw new EmptyFieldException("producto, cantidad, precio de compra y precio de venta");
        }

        if (!ValidationUtils.isNumeric(amount)) {
            throw new InvalidNumberInputException("cantidad");
        }

        if (ParserUtils.parseInteger(amount, "cantidad") <= 0) {
            throw new InvalidInputException("La cantidad debe ser mayor que cero.");
        }
    }

    public static void priceValueValidation(BigDecimal purchasePriceValue, BigDecimal salePriceValue) throws InvalidInputException {
        if (purchasePriceValue.signum() < 0 || salePriceValue.signum() < 0) {
            throw new InvalidInputException("Los precios no pueden ser negativos.");
        }
    }

    public static void productListEmptyValidation(List<DTOProductCreate> list) throws InvalidInputException {
        if (list.isEmpty()) {
            throw new InvalidInputException("Agregue al menos un producto antes de registrar la compra.");
        }
    }

    public static void rowSelectedValidation(int row) throws InvalidInputException {
        if (row < 0) {
            throw new InvalidInputException("Seleccione el producto que desea quitar de la compra.");
        }
    }

}
