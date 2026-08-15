package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface PurchaseService {

    void registerPurchase(DTOProvider dtoProvider, BigDecimal totalPaid, List<DTOProductCreate> products) throws ServiceException;
    List<DTOPurchaseDetails> findById(int id) throws ServiceException;
    List<DTOPurchaseDetails> findById(Connection connection, int id) throws ServiceException;
    List<DTOPurchaseDetails> findByAll() throws ServiceException;
    void delete(int id) throws ServiceException;
    int countRecords() throws ServiceException;

}
