package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.DebtPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAODebts;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAODebtsImpl implements DAODebts {

    private final ConnectionProvider connectionProvider;

    public DAODebtsImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Optional<DTODebtDetails> findById(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTODebtDetails(rs));
            }
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado deudas con el id: " + id, e);
        }
    }

    @Override
    public Optional<DTODebtDetails> findById(Connection connection, int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/findById.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTODebtDetails(rs));
            }
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado registros de deudas con el id: " + id, e);
        }
    }

    @Override
    public List<DTODebtDetails> findByCustomer(String customer, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/findByCustomer.sql");
        List<DTODebtDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, customer);

            try (ResultSet rs = st.executeQuery()) {
                return executeQuery(list, rs);
            }
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado registros para el cliente: " + customer, e);
        }
    }

    @Override
    public List<DTODebtDetails> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/findByAll.sql");
        List<DTODebtDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            return executeQuery(list, rs);
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado registros de deudas.", e);
        }
    }

    @Override
    public List<DTODebtDetails> findByStatus(PaymentStatus status, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/findByStatus.sql");
        List<DTODebtDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, String.valueOf(status));

            try (ResultSet rs = st.executeQuery()) {
                return executeQuery(list, rs);
            }
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado deudas para el estado " + status.getValue(), e);
        }
    }

    private List<DTODebtDetails> executeQuery(List<DTODebtDetails> list, ResultSet rs) throws SQLException {
        while (rs.next()) {
            DTODebtDetails dto = getDTODebtDetails(rs);
            list.add(dto);
        }

        return list;
    }

    private DTODebtDetails getDTODebtDetails(ResultSet rs) throws SQLException {
        return new DTODebtDetails.Builder()
                .id(rs.getInt("id"))
                .customerId(rs.getInt("customer_id"))
                .customerName(rs.getString("customer_name"))
                .customerPhone(rs.getString("phone"))
                .numSale(rs.getInt("sale_number"))
                .date(rs.getDate("sale_date").toLocalDate())
                .totalSale(rs.getBigDecimal("total_sale"))
                .totalPaid(rs.getBigDecimal("total_paid"))
                .totalDebt(rs.getBigDecimal("current_debt"))
                .status(PaymentStatus.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public int countRecords(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/debt/countRecords.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new DebtPersistenceException("No se han encontrado registros.", e);
        }
    }

}
