package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SaleDetailsPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSaleDetails;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOSaleDetailsImpl implements DAOSaleDetails {

    public DAOSaleDetailsImpl() {

    }

    @Override
    public void add(Connection connection, DTOSaleDetailsCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/saledetails/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getSaleId());
            st.setInt(2, dto.getProductId());
            st.setInt(3, dto.getAmount());
            st.setBigDecimal(4, dto.getUnitPrice());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SaleDetailsPersistenceException("Error al agregar detalle de venta", e);
        }
    }

}
