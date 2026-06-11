package com.hector.papeleriasaas.facade.interfaces;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface SaleFacade extends ICountable {

    int add(Connection connection, Integer customerId, BigDecimal totalPaid, BigDecimal totalSale) throws DAOException;
    List<DTOSaleDetails> findById(int id) throws DAOException;
    List<DTOSaleDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOSaleDetails> findByAll() throws DAOException;
    void modify(BigDecimal totalPaid, int numSale) throws DAOException;
    void modify(Connection connection, BigDecimal totalPaid, int numSale) throws DAOException;

}
