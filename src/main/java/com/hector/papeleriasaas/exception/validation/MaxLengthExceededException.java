package com.hector.papeleriasaas.exception.validation;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class MaxLengthExceededException extends InvalidInputException {

    public MaxLengthExceededException(String fieldName, String maximumValue) {
        super("El campo '" + fieldName + "' excede la longitud máxima de " + maximumValue + " caracteres");
    }

    public MaxLengthExceededException(String message, Throwable cause) {
        super(message, cause);
    }

}
