package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.PurchasePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.purchase.*;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOPurchasesImpl implements DAOPurchases {

    public DAOPurchasesImpl() {

    }

    @Override
    public int add(Connection connection, DTOPurchaseCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/insert.sql");

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, dto.getSupplierNit());
            cs.setBigDecimal(2, dto.getTotalPaid());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num_compra");
                }

                throw new SQLException("No se genero la compra");
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al agregar compra", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findById(int id) throws DAOException {
        List<DTOPurchaseDetails> list = new ArrayList<>();
        String sql = SQLRepository.get("sql/purchase/findById.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

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
    public List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/findById.sql");
        List<DTOPurchaseDetails> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

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
    public List<DTOPurchaseDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/purchase/findByAll.sql");
        List<DTOPurchaseDetails> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                DTOPurchaseDetails dto = getDTOPurchaseDetails(rs);
                list.add(dto);
            }

            return list;
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al obtener todas las compras", e);
        }
    }

    private DTOPurchaseDetails getDTOPurchaseDetails(ResultSet rs) throws SQLException {
        return new DTOPurchaseDetails.Builder()
                .numPurchase(rs.getInt("num_compra"))
                .productDescription(rs.getString("producto"))
                .productId(rs.getInt("id_producto"))
                .quantityPurchased(rs.getInt("cant_comprada"))
                .purchasePrice(rs.getBigDecimal("precio_compra"))
                .salePrice(rs.getBigDecimal("precio_venta"))
                .date(rs.getDate("fecha").toLocalDate())
                .supplier(rs.getString("proveedor"))
                .supplierNit(rs.getInt("nit_proveedor"))
                .totalPaid(rs.getBigDecimal("total_compra"))
                .earnings(rs.getBigDecimal("ganancias"))
                .build();
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/purchase/delete.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, id);
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("compra no existe con numero: " + id);
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al eliminar compra", e);
        }
    }

    @Override
    public int countRecords() throws DAOException {
        String sql = SQLRepository.get("sql/purchase/countRecords.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new PurchasePersistenceException("Error al contar compras", e);
        }
    }

}
