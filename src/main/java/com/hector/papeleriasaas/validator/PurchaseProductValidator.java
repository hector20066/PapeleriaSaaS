package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.utils.ValidationUtils;

public class PurchaseProductValidator {

    public static void validations(String nit, String rSocial, String address, String phone, String email) throws InvalidInputException {
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

}
