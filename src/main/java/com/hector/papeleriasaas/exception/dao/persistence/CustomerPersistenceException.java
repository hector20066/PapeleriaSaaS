package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CustomerPersistenceException extends PersistenceException {

    public CustomerPersistenceException() {
        super();
    }

    public CustomerPersistenceException(String message) {
        super(message);
    }

    public CustomerPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
