package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOCustomers extends IModifiable<DTOCustomerCreate>,
        IListable<DTOCustomerDetails>,
        ICountable,
        IRemovable {

    int add(Connection connection, DTOCustomerCreate dto) throws DAOException;
    Optional<DTOCustomerDetails> findByName(String name) throws DAOException;

}
