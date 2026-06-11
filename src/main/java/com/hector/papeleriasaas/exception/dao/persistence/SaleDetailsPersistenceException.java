package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleDetailsPersistenceException extends PersistenceException {

    public SaleDetailsPersistenceException() {
        super();
    }

    public SaleDetailsPersistenceException(String message) {
        super(message);
    }

    public SaleDetailsPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
