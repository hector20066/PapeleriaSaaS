package com.hector.papeleriasaas.exception.auth;

public class IncorrectPasswordException extends Exception {

    public IncorrectPasswordException() {
        super("La contraseña es incorrecta");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
