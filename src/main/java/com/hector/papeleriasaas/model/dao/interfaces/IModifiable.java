package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface IModifiable<T> {

    void modify(Connection connection, T dto) throws DAOException;

}
