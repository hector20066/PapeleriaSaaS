package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTOInvoiceCreate;

import java.sql.Connection;

public interface InvoiceFacade {

    int add(Connection connection, DTOInvoiceCreate dto) throws DAOException;

}
