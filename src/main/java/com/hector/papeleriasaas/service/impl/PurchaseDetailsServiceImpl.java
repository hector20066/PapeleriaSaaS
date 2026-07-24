package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchaseDetails;
import com.hector.papeleriasaas.model.dto.DTOPurchaseDetailsCreate;
import com.hector.papeleriasaas.service.interfaces.PurchaseDetailsService;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {

    public final DAOPurchaseDetails daoPurchaseDetails;

    public PurchaseDetailsServiceImpl(DAOPurchaseDetails daoPurchaseDetails) {
        this.daoPurchaseDetails = daoPurchaseDetails;
    }

    @Override
    public void add(Connection connection, int purchaseNum, int productId, int amount, BigDecimal unitPrice) throws DAOException {
        DTOPurchaseDetailsCreate dto = new DTOPurchaseDetailsCreate();
        dto.setPurchaseId(purchaseNum);
        dto.setProductId(productId);
        dto.setAmount(amount);
        dto.setUnitPrice(unitPrice);

        daoPurchaseDetails.add(connection, dto);
    }

}
