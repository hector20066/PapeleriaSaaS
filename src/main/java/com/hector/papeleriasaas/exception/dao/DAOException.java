package com.hector.papeleriasaas.exception.dao;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOException extends Exception {

    public DAOException() {
        super("Error en la capa de acceso a datos");
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
