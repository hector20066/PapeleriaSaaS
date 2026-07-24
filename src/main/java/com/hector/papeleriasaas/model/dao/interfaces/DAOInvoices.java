package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;

import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface DAOInvoices {

    int add(Connection connection, DTOInvoiceCreate dto) throws DAOException;

}
