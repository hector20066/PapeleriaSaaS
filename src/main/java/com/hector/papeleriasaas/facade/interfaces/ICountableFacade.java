package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

@FunctionalInterface
public interface ICountableFacade {

    int countRecords() throws DAOException;

}
