package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOPurchaseDetailsCreate;

import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface DAOPurchaseDetails {

    void add(Connection connection, DTOPurchaseDetailsCreate dto) throws DAOException;

}
