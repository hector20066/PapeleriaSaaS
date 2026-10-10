package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOInvoices;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.service.interfaces.InvoicesService;

import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class InvoicesServiceImpl implements InvoicesService {

    private final DAOInvoices daoInvoices;

    public InvoicesServiceImpl(DAOInvoices daoInvoices) {
        this.daoInvoices = daoInvoices;
    }

    @Override
    public int add(Connection connection, DTOInvoiceCreate dto) throws ServiceException {
        try {
            return daoInvoices.add(connection, dto);
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
