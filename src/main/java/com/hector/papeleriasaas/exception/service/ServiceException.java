package com.hector.papeleriasaas.exception.service;

/**
 * Error de aplicación que puede ser comunicado por la capa de servicios.
 * La causa conserva el detalle técnico para el registro interno, pero no debe
 * presentarse directamente a la interfaz de usuario.
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
