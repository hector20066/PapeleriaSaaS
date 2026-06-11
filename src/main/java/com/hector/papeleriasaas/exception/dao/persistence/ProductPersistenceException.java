package com.hector.papeleriasaas.exception.dao.persistence;

import com.hector.papeleriasaas.exception.dao.PersistenceException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProductPersistenceException extends PersistenceException {

    public ProductPersistenceException() {
        super();
    }

    public ProductPersistenceException(String message) {
        super(message);
    }

    public ProductPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
