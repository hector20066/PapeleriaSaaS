package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchasePersistenceException extends PersistenceException {

    public PurchasePersistenceException() {
        super();
    }

    public PurchasePersistenceException(String message) {
        super(message);
    }

    public PurchasePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
