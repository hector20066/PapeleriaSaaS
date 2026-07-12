package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface ICountable {

    int countRecords(int companyId) throws DAOException;

}
