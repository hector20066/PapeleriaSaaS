package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.PurchaseDetailsPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchaseDetails;
import com.hector.papeleriasaas.model.dto.DTOPurchaseDetailsCreate;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOPurchaseDetailsImpl implements DAOPurchaseDetails {

    public DAOPurchaseDetailsImpl() {

    }

    @Override
    public void add(Connection connection, DTOPurchaseDetailsCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/purchasedetails/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getPurchaseNum());
            st.setInt(2, dto.getProductId());
            st.setInt(3, dto.getAmount());
            st.setBigDecimal(4, dto.getUnitPrice());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PurchaseDetailsPersistenceException("Error al agregar detalle de compra", e);
        }
    }

}
