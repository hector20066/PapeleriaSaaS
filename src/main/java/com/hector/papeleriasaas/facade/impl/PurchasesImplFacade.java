package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.PurchasesFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOPurchasesImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.purchase.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchasesImplFacade implements PurchasesFacade {

    private final DAOPurchases daoPurchases;

    public PurchasesImplFacade() {
        this.daoPurchases = new DAOPurchasesImpl();
    }

    @Override
    public int add(Connection connection, int supplierNit, BigDecimal totalPaid) throws DAOException {
        DTOPurchaseCreate dto = new DTOPurchaseCreate();
        dto.setSupplierNit(supplierNit);
        dto.setTotalPaid(totalPaid);

        return daoPurchases.add(connection, dto);
    }

    @Override
    public List<DTOPurchaseDetails> findById(int id) throws DAOException {
        return daoPurchases.findById(id);
    }

    @Override
    public List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException {
        return daoPurchases.findById(connection, id);
    }

    @Override
    public List<DTOPurchaseDetails> findByAll() throws DAOException {
        return daoPurchases.findByAll();
    }

    @Override
    public void delete(int id) throws DAOException {
        daoPurchases.delete(id);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoPurchases.countRecords();
    }

}
