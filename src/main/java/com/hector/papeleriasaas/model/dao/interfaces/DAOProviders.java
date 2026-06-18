package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOSupplier;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOProviders extends IListable<DTOSupplier>,
        IModifiable<DTOSupplier>,
        IRemovable,
        ICountable {

    void add(Connection connection, DTOSupplier dto) throws DAOException;
    Optional<DTOSupplier> findByName(String name) throws DAOException;

}
