package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.purchase.*;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOPurchases extends IRemovable,
        ICountable {

    int add(Connection connection, DTOPurchaseCreate dto) throws DAOException;
    List<DTOPurchaseDetails> findById(int id) throws DAOException;
    List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOPurchaseDetails> findByAll() throws DAOException;

}
