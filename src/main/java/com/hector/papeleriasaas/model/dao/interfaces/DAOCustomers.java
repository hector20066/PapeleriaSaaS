package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOCustomer;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOCustomers extends IModifiable<DTOCustomer>,
        IListable<DTOCustomer>,
        ICountable,
        IRemovable {

    int add(Connection connection, DTOCustomer dto) throws DAOException;
    Optional<DTOCustomer> findByName(String name) throws DAOException;

}
