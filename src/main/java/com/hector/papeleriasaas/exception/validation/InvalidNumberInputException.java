package com.hector.papeleriasaas.exception.validation;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class InvalidNumberInputException extends InvalidInputException {

    public InvalidNumberInputException(String fieldName) {
        super("El campo '" + fieldName + "' debe contener un valor numérico");
    }

    public InvalidNumberInputException(String message, Throwable cause) {
        super(message, cause);
    }

}
