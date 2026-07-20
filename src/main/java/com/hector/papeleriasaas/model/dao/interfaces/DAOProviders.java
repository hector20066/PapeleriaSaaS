package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOProviders extends IListable<DTOProvider>,
        IModifiable<DTOProvider>,
        IRemovable,
        ICountable {

    int add(Connection connection, DTOProvider dto) throws DAOException;
    Optional<DTOProvider> findByName(String name, int companyId) throws DAOException;

}
