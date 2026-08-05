package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SalePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSales;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleUpdate;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOSalesImpl implements DAOSales {

    private final ConnectionProvider connectionProvider;

    public DAOSalesImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
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

            cs.setBigDecimal(2, dto.getTotalPaid());
            cs.setBigDecimal(3, dto.getTotalSale());
            cs.setInt(4, dto.getCompanyId());

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("sale_number");
                }

                throw new SQLException("No se genero la venta");
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al agregar venta", e);
        }
    }

    @Override
    public List<DTOSaleDetails> findById(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/sale/findById.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

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
    public List<DTOSaleDetails> findById(Connection connection, int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/sale/findById.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

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
    public List<DTOSaleDetails> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/sale/findByAll.sql");
        List<DTOSaleDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOSaleDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al obtener todas las ventas", e);
        }
    }

    private DTOSaleDetails getDTOSaleDetails(ResultSet rs) throws SQLException {
        return new DTOSaleDetails.Builder()
                .numSale(rs.getInt("sale_number"))
                .productId(rs.getInt("product_id"))
                .productDescription(rs.getString("product"))
                .quantitySold(rs.getInt("quantity_sold"))
                .date(rs.getDate("sale_date").toLocalDate())
                .customer(rs.getString("customer"))
                .totalSale(rs.getBigDecimal("total_sale"))
                .build();
    }

    @Override
    public void modify(DTOSaleUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/sale/modify.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            modifyData(st, dto);
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al modificar venta", e);
        }
    }

    @Override
    public void modify(Connection connection, DTOSaleUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/sale/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            modifyData(st, dto);
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al modificar venta", e);
        }
    }

    private void modifyData(PreparedStatement st, DTOSaleUpdate dto) throws SQLException {
        st.setBigDecimal(1, dto.getTotalPaid());
        st.setInt(2, dto.getNumSale());
        st.setInt(3, dto.getCompanyId());

        if (st.executeUpdate() == 0) {
            throw new SQLException("venta no existe con numero: " + dto.getNumSale());
        }
    }

    @Override
    public int countRecords(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/sale/countRecords.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? rs.getInt("total_records")
                        : 0;
            }
        } catch (SQLException e) {
            throw new SalePersistenceException("Error al contar ventas", e);
        }
    }

}
