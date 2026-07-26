package com.hector.papeleriasaas.exception.auth;

public class IncorrectPasswordException extends AuthException {

    public IncorrectPasswordException() {
        super("La contraseña es incorrecta");
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
