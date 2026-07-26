package com.hector.papeleriasaas.exception.auth;

public class InactiveUserException extends Exception {

    public InactiveUserException() {
        super("El usuario se encuentra inactivo");
    }

    public InactiveUserException(String message) {
        super(message);
    }

}
