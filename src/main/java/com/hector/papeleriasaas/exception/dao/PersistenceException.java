package com.hector.papeleriasaas.exception.dao;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PersistenceException extends DAOException {

    public PersistenceException() {
        super("Error durante operación de persistencia");
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
