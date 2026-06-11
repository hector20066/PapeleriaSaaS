package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SupplierPersistenceException extends PersistenceException {

    public SupplierPersistenceException() {
        super();
    }

    public SupplierPersistenceException(String message) {
        super(message);
    }

    public SupplierPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
