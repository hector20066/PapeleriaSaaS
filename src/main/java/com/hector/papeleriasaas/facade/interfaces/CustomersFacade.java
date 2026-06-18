package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dao.interfaces.IListable;
import com.hector.papeleriasaas.model.dto.DTOCustomer;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CustomerFacade extends IListable<DTOCustomer>,
        ICountable {

    void add(Connection connection, int id, String name, String phone) throws DAOException;
    Optional<DTOCustomer> findByName(String name) throws DAOException;
    void modify(Connection connection, String phone, int id) throws DAOException;

}
