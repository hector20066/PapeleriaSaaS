package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface IRemovable {

    void delete(int id) throws DAOException;

}
