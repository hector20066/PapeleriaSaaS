package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSaleDetails;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleDetailsService {

    private final DAOSaleDetails daoSaleDetails;

    public SaleDetailsService(DAOSaleDetails daoSaleDetails) {
        this.daoSaleDetails = daoSaleDetails;
    }

    public void add(Connection connection, int numSale, int productId, int amount, BigDecimal unitPrice) throws DAOException {
        DTOSaleDetailsCreate dto = new DTOSaleDetailsCreate();
        dto.setSaleId(numSale);
        dto.setProductId(productId);
        dto.setAmount(amount);
        dto.setUnitPrice(unitPrice);

        daoSaleDetails.add(connection, dto);
    }

}
