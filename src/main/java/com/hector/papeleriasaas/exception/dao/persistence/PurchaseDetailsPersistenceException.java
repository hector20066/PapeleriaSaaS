package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetailsPersistenceException extends PersistenceException {

    public PurchaseDetailsPersistenceException() {
        super();
    }

    public PurchaseDetailsPersistenceException(String message) {
        super(message);
    }

    public PurchaseDetailsPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
