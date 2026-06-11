package com.hector.papeleriasaas.exception.validation;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class EmptyFieldException extends InvalidInputException {

    public EmptyFieldException(String fieldName) {
        super("El campo '" + fieldName + "' es requerido y no puede estar vacío");
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

}
