package com.hector.papeleriasaas.exception.validation;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("Entrada inválida");
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

}
