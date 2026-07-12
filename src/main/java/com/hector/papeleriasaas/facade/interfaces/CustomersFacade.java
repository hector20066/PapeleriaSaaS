package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dao.interfaces.IListable;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CustomersFacade extends IListableFacade<DTOCustomerDetails>,
        ICountableFacade {

    void add(Connection connection, int id, String name, String phone) throws DAOException;
    Optional<DTOCustomerDetails> findByName(String name) throws DAOException;
    void modify(Connection connection, String phone, int id) throws DAOException;

}
