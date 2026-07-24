package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;

import java.sql.Connection;

public interface InvoicesService {

    int add(Connection connection, DTOInvoiceCreate dto) throws DAOException;

}
