package com.hector.papeleriasaas.exception.service;

public class ServiceDataNotFoundException extends ServiceException {

    public ServiceDataNotFoundException() {
        super("No se encontró la información solicitada.");
    }

    public ServiceDataNotFoundException(String message) {
        super(message);
    }

    public ServiceDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
