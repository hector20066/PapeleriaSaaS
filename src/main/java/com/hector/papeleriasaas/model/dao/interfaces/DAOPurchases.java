package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOPurchases extends IRemovable,
        ICountable {

    int add(Connection connection, DTOPurchaseCreate dto) throws DAOException;
    List<DTOPurchaseDetails> findById(int id, int companyId) throws DAOException;
    List<DTOPurchaseDetails> findById(Connection connection, int id, int companyId) throws DAOException;
    List<DTOPurchaseDetails> findByAll(int companyId) throws DAOException;

}
