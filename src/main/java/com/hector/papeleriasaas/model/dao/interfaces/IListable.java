package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface IListable<T> {

    Optional<T> findById(int id) throws DAOException;
    Optional<T> findById(Connection connection, int id) throws DAOException;
    List<T> findByAll() throws DAOException;

}
