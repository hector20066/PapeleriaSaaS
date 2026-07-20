package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProvidersFacade extends IListableFacade<DTOProvider>,
        IRemovableFacade,
        ICountableFacade {

    void add(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;
    Optional<DTOProvider> findByName(String name) throws DAOException;
    void modify(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;

}
