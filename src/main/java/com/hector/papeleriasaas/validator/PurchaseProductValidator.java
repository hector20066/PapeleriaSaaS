package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PurchaseProductValidator {

    public static void registerPurchaseValidation(String nit, String rSocial, String address) throws InvalidInputException {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("R. Social", rSocial);
        fields.put("Dirección", address);

        List<String> emptyFields = ValidationUtils.getEmptyFields(fields);

        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldException(emptyFields);
        }

        invalidInputValidation(rSocial, address);

        if (!nit.isBlank() && !nit.equalsIgnoreCase("Nit")) {
            if (!ValidationUtils.isNumeric(nit)) {
                throw new InvalidNumberInputException("NIT del proveedor");
            }
        }
    }

    private static void invalidInputValidation(String rSocial, String address) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(rSocial, "R. Social")) {
            throw new InvalidInputException("La razón social del proveedor es requerida, ingrese un valor valido");
        }

        if (ValidationUtils.isInvalidInput(address, "Direccion")) {
            throw new InvalidInputException("La dirección del proveedor es requerida, ingrese un valor valido");
        }
    }

    public static void addProductValidation(String product, String amount, String purchasePrice, String salePrice) throws InvalidInputException {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("Producto", product);
        fields.put("Cantidad", amount);
        fields.put("Precio de compra", purchasePrice);
        fields.put("Precio de venta", salePrice);

        List<String> emptyFields = ValidationUtils.getEmptyFields(fields);

        if (!emptyFields.isEmpty()) {
            throw new EmptyFieldException(emptyFields);
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
