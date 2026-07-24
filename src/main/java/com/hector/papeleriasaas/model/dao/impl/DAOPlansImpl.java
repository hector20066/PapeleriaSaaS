package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPlans;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.enums.BillingCycle;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOPlansImpl implements DAOPlans {

    private final ConnectionProvider connectionProvider;

    public DAOPlansImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(DTOPlanCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/plan/insert.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, dto.getName());
            st.setBigDecimal(2, dto.getPrice());
            st.setString(3, dto.getDescription());
            st.setString(4, String.valueOf(dto.getBillingCycle()));

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating plan failed, no rows affected.");
            }

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new PersistenceException();
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOPlanDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/plan/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOPlanDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public List<DTOPlanDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/plan/findByAll.sql");
        List<DTOPlanDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(getDTOPlanDetails(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOPlanDetails getDTOPlanDetails(ResultSet rs) throws SQLException {
        return new DTOPlanDetails.Builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getBigDecimal("price"))
                .description(rs.getString("description"))
                .billingCycle(BillingCycle.valueOf(rs.getString("billing_cycle")))
                .build();
    }

    @Override
    public void modify(DTOPlanDetails dto) throws DAOException {
        String sql = SQLRepository.get("sql/plan/modify.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getName());
            st.setBigDecimal(2, dto.getPrice());
            st.setString(3, dto.getDescription());
            st.setString(4, String.valueOf(dto.getBillingCycle()));
            st.setInt(5, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/plan/delete.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

}
