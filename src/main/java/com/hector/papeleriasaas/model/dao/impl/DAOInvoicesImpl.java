package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SalePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOInvoices;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOInvoicesImpl implements DAOInvoices {

    @Override
    public int add(Connection connection, DTOInvoiceCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/invoice/insert.sql");

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, dto.getSaleId());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("last_num_factura");
                }

                throw new SQLException("No se genero la factura");
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al agregar la factura", e);
        }
    }

}
