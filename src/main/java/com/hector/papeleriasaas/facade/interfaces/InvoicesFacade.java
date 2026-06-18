package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;

import java.sql.Connection;

public interface InvoicesFacade {

    int add(Connection connection, DTOInvoiceCreate dto) throws DAOException;

}
