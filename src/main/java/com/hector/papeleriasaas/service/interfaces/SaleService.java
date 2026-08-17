package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface SaleService {

    int registerSale(Integer customerId, String customerName, String customerPhone, String customerEmail, BigDecimal amountPaid,
                     BigDecimal totalSale, List<DTOSaleDetailsCreate> products) throws ServiceException;

    String checkLowStock() throws ServiceException;
    List<DTOSaleDetails> findById(int id) throws ServiceException;
    List<DTOSaleDetails> findById(Connection connection, int id) throws ServiceException;
    List<DTOSaleDetails> findByAll() throws ServiceException;
    void modify(BigDecimal currentBalance, BigDecimal currentDebt, BigDecimal pass, int numSale) throws ServiceException;
    void modify(Connection connection, BigDecimal totalPaid, int numSale) throws ServiceException;
    int countRecords() throws ServiceException;

}
