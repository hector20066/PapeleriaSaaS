package com.hector.papeleriasaas.exception.dao;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DataNotFoundException extends DAOException {

    public DataNotFoundException() {
        super("Datos no encontrados");
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
