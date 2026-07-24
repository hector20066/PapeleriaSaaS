package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PurchaseService {

    void registerPurchase(DTOProvider dtoProvider, BigDecimal totalPaid, List<DTOProductCreate> products) throws SQLException, DAOException;
    List<DTOPurchaseDetails> findById(int id) throws DAOException;
    List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOPurchaseDetails> findByAll() throws DAOException;
    void delete(int id) throws DAOException;
    int countRecords() throws DAOException;

}
