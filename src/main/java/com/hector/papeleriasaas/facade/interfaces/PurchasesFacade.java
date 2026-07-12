package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dao.interfaces.IRemovable;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface PurchasesFacade extends IRemovableFacade,
        ICountableFacade {

    int add(Connection connection, int supplierNit, BigDecimal totalPaid) throws DAOException;
    List<DTOPurchaseDetails> findById(int id) throws DAOException;
    List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOPurchaseDetails> findByAll() throws DAOException;

}
