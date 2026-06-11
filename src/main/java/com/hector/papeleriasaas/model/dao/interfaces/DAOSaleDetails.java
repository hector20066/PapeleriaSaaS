package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;

import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface DAOSaleDetails {

    void add(Connection connection, DTOSaleDetailsCreate dto) throws DAOException;

}
