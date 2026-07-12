package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.SaleDetailsFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOSaleDetailsImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSaleDetails;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleDetailsImplFacade implements SaleDetailsFacade {

    private final DAOSaleDetails daoSaleDetails;

    public SaleDetailsImplFacade(DAOSaleDetails daoSaleDetails) {
        this.daoSaleDetails = daoSaleDetails;
    }

    @Override
    public void add(Connection connection, int numSale, int productId, int amount, BigDecimal unitPrice) throws DAOException {
        DTOSaleDetailsCreate dto = new DTOSaleDetailsCreate();
        dto.setSaleId(numSale);
        dto.setProductId(productId);
        dto.setAmount(amount);
        dto.setUnitPrice(unitPrice);

        daoSaleDetails.add(connection, dto);
    }

}
