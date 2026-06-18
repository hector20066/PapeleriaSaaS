package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dao.interfaces.IListable;
import com.hector.papeleriasaas.model.dao.interfaces.IRemovable;
import com.hector.papeleriasaas.model.dto.DTOSupplier;

import java.sql.Connection;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface SupplierFacade extends IListable<DTOSupplier>,
        IRemovable,
        ICountable {

    void add(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;
    Optional<DTOSupplier> findByName(String name) throws DAOException;
    void modify(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;

}
