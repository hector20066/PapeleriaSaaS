package com.hector.papeleriasaas.exception.validation;

import java.util.Collection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class EmptyFieldException extends InvalidInputException {

    public EmptyFieldException(Collection<String> fields) {
        super("Los siguientes campos son requeridos: " + String.join(", ", fields) + ".");
    }

    public EmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

}
