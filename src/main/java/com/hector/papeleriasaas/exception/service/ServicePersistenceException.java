package com.hector.papeleriasaas.exception.service;

public class ServicePersistenceException extends ServiceException {

    public ServicePersistenceException(String message) {
        super(message);
    }

    public ServicePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
