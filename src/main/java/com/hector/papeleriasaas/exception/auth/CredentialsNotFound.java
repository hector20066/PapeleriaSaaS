package com.hector.papeleriasaas.exception.auth;

public class CredentialsNotFound extends AuthException {

    public CredentialsNotFound() {
        super("Credenciales de inicio de sesión incorrectas");
    }

    public CredentialsNotFound(String message) {
        super(message);
    }

}
