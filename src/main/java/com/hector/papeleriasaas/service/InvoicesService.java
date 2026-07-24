package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOInvoices;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;

import java.sql.Connection;

public class InvoicesService {

    private final DAOInvoices daoInvoices;

    public InvoicesService(DAOInvoices daoInvoices) {
        this.daoInvoices = daoInvoices;
    }

    public int add(Connection connection, DTOInvoiceCreate dto) throws DAOException {
        return daoInvoices.add(connection, dto);
    }

}
