package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

@FunctionalInterface
public interface IRemovableFacade {

    void delete(int id) throws DAOException;

}
