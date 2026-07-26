package com.hector.papeleriasaas.exception.auth;

public class AuthException extends Exception {

    public AuthException() {
        super("Error de autenticación");
    }

    public AuthException(String message) {
        super(message);
    }

}
