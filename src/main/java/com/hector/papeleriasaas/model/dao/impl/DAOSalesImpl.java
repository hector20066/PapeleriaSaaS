package com.hector.papeleriasaas.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SalePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSales;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleUpdate;
import com.hector.papeleriasaas.utils.SQLRepository;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOSalesImpl implements DAOSales {

    public DAOSalesImpl() {

    }

    @Override
    public int add(Connection connection, DTOSaleCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/sale/insert.sql");

        try (CallableStatement cs = connection.prepareCall(sql)) {
            if (dto.getCustomerId() != null) {
                cs.setInt(1, dto.getCustomerId());
            } else {
                cs.setNull(1, Types.INTEGER);
            }
            cs.setBigDecimal(2, dto.getTotalSale());
            cs.setBigDecimal(3, dto.getTotalPaid());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("num_venta");
                }

                throw new SQLException("No se genero la venta");
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al agregar venta", e);
        }
    }

    @Override
    public List<DTOSaleDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/sale/findById.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOSaleDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al buscar venta por numero", e);
        }
    }

    @Override
    public List<DTOSaleDetails> findById(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/sale/findById.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOSaleDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al buscar venta por numero", e);
        }
    }

    @Override
    public List<DTOSaleDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/sale/findByAll.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                DTOSaleDetails dto = getDTOSaleDetails(rs);
                list.add(dto);
            }

            return list;
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al obtener todas las ventas", e);
        }
    }

    private DTOSaleDetails getDTOSaleDetails(ResultSet rs) throws SQLException {
        return new DTOSaleDetails.Builder()
                .numSale(rs.getInt("num_venta"))
                .productId(rs.getInt("id_producto"))
                .productDescription(rs.getString("producto"))
                .quantitySold(rs.getInt("cantidad_vendida"))
                .date(rs.getDate("fecha").toLocalDate())
                .customer(rs.getString("cliente"))
                .totalSale(rs.getBigDecimal("total_venta"))
                .build();
    }

    @Override
    public void modify(DTOSaleUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/sale/modify.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setBigDecimal(1, dto.getTotalPaid());
                st.setInt(2, dto.getNumSale());
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("venta no existe con numero: " + dto.getNumSale());
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al modificar venta", e);
        }
    }

    @Override
    public void modify(Connection connection, DTOSaleUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/sale/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setBigDecimal(1, dto.getTotalPaid());
            st.setInt(2, dto.getNumSale());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("venta no existe con numero: " + dto.getNumSale());
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al modificar venta", e);
        }
    }

    @Override
    public int countRecords() throws DAOException {
        String sql = SQLRepository.get("sql/sale/countRecords.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al contar ventas", e);
        }
    }

}
