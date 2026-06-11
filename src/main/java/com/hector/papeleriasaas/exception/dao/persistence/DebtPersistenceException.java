package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DebtPersistenceException extends PersistenceException {

    public DebtPersistenceException() {
        super();
    }

    public DebtPersistenceException(String message) {
        super(message);
    }

    public DebtPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
