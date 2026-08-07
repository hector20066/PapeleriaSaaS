package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.PurchasePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOPurchasesImpl implements DAOPurchases {

    private final ConnectionProvider connectionProvider;

    public DAOPurchasesImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(Connection connection, DTOPurchaseCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/insert.sql");

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, dto.getProviderId());
            cs.setBigDecimal(2, dto.getTotalPurchase());
            cs.setInt(3, dto.getCompanyId());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("purchase_id");
                }

                throw new SQLException("No se genero la compra");
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al agregar compra", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findById(int id, int companyId) throws DAOException {
        List<DTOPurchaseDetails> list = new ArrayList<>();
        String sql = SQLRepository.get("sql/purchase/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOPurchaseDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al buscar compra por numero", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findById(Connection connection, int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/findById.sql");
        List<DTOPurchaseDetails> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOPurchaseDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al buscar compra por numero", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/findByAll.sql");
        List<DTOPurchaseDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql) ) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOPurchaseDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al obtener todas las compras", e);
        }
    }

    private DTOPurchaseDetails getDTOPurchaseDetails(ResultSet rs) throws SQLException {
        return new DTOPurchaseDetails.Builder()
                .numPurchase(rs.getInt("purchase_number"))
                .productDescription(rs.getString("product"))
                .productId(rs.getInt("product_id"))
                .quantityPurchased(rs.getInt("quantity_purchased"))
                .purchasePrice(rs.getBigDecimal("purchase_price"))
                .salePrice(rs.getBigDecimal("sale_price"))
                .date(rs.getDate("purchase_date").toLocalDate())
                .provider(rs.getString("provider"))
                .providerNit(rs.getInt("provider_nit"))
                .totalPaid(rs.getBigDecimal("total_purchase"))
                .earnings(rs.getBigDecimal("earnings"))
                .build();
    }

    @Override
    public void delete(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/delete.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            if (st.executeUpdate() == 0) {
                throw new SQLException("compra no existe con numero: " + id);
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al eliminar compra", e);
        }
    }

    @Override
    public int countRecords(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/countRecords.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? rs.getInt("total_records")
                        : 0;
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al contar compras", e);
        }
    }

}
