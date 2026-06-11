package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.InvoiceFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOInvoiceImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOInvoice;
import com.hector.papeleriasaas.model.dto.DTOInvoiceCreate;

import java.sql.Connection;

public class InvoiceImplFacade implements InvoiceFacade {

    private final DAOInvoice daoInvoice;

    public InvoiceImplFacade() {
        this.daoInvoice = new DAOInvoiceImpl();
    }

    @Override
    public int add(Connection connection, DTOInvoiceCreate dto) throws DAOException {
        return daoInvoice.add(connection, dto);
    }

}
