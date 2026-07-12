package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.InvoicesFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOInvoicesImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOInvoices;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;

import java.sql.Connection;

public class InvoicesImplFacade implements InvoicesFacade {

    private final DAOInvoices daoInvoices;

    public InvoicesImplFacade(DAOInvoices daoInvoices) {
        this.daoInvoices = daoInvoices;
    }

    @Override
    public int add(Connection connection, DTOInvoiceCreate dto) throws DAOException {
        return daoInvoices.add(connection, dto);
    }

}
