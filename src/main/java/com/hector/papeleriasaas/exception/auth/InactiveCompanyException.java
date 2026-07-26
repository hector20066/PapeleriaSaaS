package com.hector.papeleriasaas.exception.auth;

public class InactiveCompanyException extends Exception {

    public InactiveCompanyException() {
        super("La empresa se encuentra inactiva");
    }

    public InactiveCompanyException(String message) {
        super(message);
    }

}
