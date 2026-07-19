package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOCustomers extends IModifiable<DTOCustomerCreate>,
        ICountable,
        IRemovable {

    int add(Connection connection, DTOCustomerCreate dto) throws DAOException;
    Optional<DTOCustomerDetails> findByName(String name, int companyId) throws DAOException;
    Optional<DTOCustomerDetails> findById(int id) throws DAOException;
    Optional<DTOCustomerDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOCustomerDetails> findByAll(int companyId) throws DAOException;

}
