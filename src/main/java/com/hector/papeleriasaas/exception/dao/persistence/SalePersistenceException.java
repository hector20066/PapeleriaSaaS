package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SalePersistenceException extends PersistenceException {

    public SalePersistenceException() {
        super();
    }

    public SalePersistenceException(String message) {
        super(message);
    }

    public SalePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
