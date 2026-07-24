package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SaleService {

    int registerSale(Integer customerId, String customerName, String customerPhone, BigDecimal amountPaid, BigDecimal totalSale, List<DTOSaleDetailsCreate> products) throws SQLException, DAOException;
    String checkLowStock() throws DAOException;
    List<DTOSaleDetails> findById(int id) throws DAOException;
    List<DTOSaleDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOSaleDetails> findByAll() throws DAOException;
    void modify(BigDecimal totalPaid, int numSale) throws DAOException;
    void modify(Connection connection, BigDecimal totalPaid, int numSale) throws DAOException;
    int countRecords() throws DAOException;

}
