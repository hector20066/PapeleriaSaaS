package com.hector.papeleriasaas.facade.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOSalesImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSales;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleUpdate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SalesImplFacade implements SalesFacade {

    private final DAOSales daoSales;
    private int companyId = 0;

    public SalesImplFacade(DAOSales daoSales) {
        this.daoSales = daoSales;
    }

    @Override
    public int add(Connection connection, Integer customerId, BigDecimal totalPaid, BigDecimal totalSale) throws DAOException {
        DTOSaleCreate dto = new DTOSaleCreate();
        dto.setCustomerId(customerId);
        dto.setTotalPaid(totalPaid);
        dto.setTotalSale(totalSale);
        dto.setCompanyId(companyId);

        return daoSales.add(connection, dto);
    }

    @Override
    public List<DTOSaleDetails> findById(int id) throws DAOException {
        return daoSales.findById(id, companyId);
    }

    @Override
    public List<DTOSaleDetails> findById(Connection connection, int id) throws DAOException {
        return daoSales.findById(connection, id, companyId);
    }

    @Override
    public List<DTOSaleDetails> findByAll() throws DAOException {
        return daoSales.findByAll(companyId);
    }

    @Override
    public void modify(BigDecimal totalPaid, int numSale) throws DAOException {
        DTOSaleUpdate dto = new DTOSaleUpdate();
        dto.setTotalPaid(totalPaid);
        dto.setNumSale(numSale);

        daoSales.modify(dto);
    }

    @Override
    public void modify(Connection connection, BigDecimal totalPaid, int numSale) throws DAOException {
        DTOSaleUpdate dto = new DTOSaleUpdate();
        dto.setTotalPaid(totalPaid);
        dto.setNumSale(numSale);

        daoSales.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoSales.countRecords(companyId);
    }

}
