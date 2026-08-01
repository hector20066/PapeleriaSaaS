package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.*;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOCompaniesImpl implements DAOCompanies {

    private final ConnectionProvider connectionProvider;

    public DAOCompaniesImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(Connection connection, DTOCompanyCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (dto.getNit() != null) {
                st.setInt(1, dto.getNit());
            } else {
                st.setInt(1, Types.INTEGER);
            }

            st.setString(2, dto.getName());
            st.setString(3, dto.getAddress());
            st.setString(4, dto.getPhone());
            st.setString(5, dto.getEmail());
            st.setDate(6, Date.valueOf(dto.getRegistrationDate()));
            st.setString(7, String.valueOf(dto.getStatus()));
            st.setInt(8, dto.getPlanId());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating company failed, no rows affected.");
            }

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Creating company failed, no ID contained.");
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error al agregar la empresa", e);
        }
    }

    @Override
    public Optional<DTOCompanySimpleDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/company/adminFilters/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOCompanySimpleDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOCompanySimpleDetails> findByName(String name) throws DAOException {
        String sql = SQLRepository.get("sql/company/adminFilters/findByName.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOCompanySimpleDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOCompanySimpleDetails> findByNit(int nit) throws DAOException {
        String sql = SQLRepository.get("sql/company/adminFilters/findByNit.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, nit);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOCompanySimpleDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOCompanyDetails> findCompanyInfo(int id) throws DAOException {
        String sql = SQLRepository.get("sql/company/filtersForBusinesses/findCompanyInfo.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOCompanyDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOCompanyDetails> findCompanyInfo(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/company/filtersForBusinesses/findCompanyInfo.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOCompanyDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOCompanyDetails getDTOCompanyDetails(ResultSet rs) throws SQLException {
        return new DTOCompanyDetails.Builder()
                .id(rs.getInt("id"))
                .nit(rs.getInt("nit"))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .registrationDate(rs.getDate("registration_date").toLocalDate())
                .status(Status.valueOf(rs.getString("status")))
                .planName(rs.getString("plan_name"))
                .planPrice(rs.getBigDecimal("plan_price"))
                .build();
    }

    @Override
    public List<DTOCompanySimpleDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/company/adminFilters/findByPaymentsReport.sql");
        List<DTOCompanySimpleDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(getDTOCompanySimpleDetails(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOCompanySimpleDetails getDTOCompanySimpleDetails(ResultSet rs) throws SQLException {
        return new DTOCompanySimpleDetails.Builder()
                .id(rs.getInt("id"))
                .nit(rs.getInt("nit"))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .registrationDate(rs.getDate("registration_date").toLocalDate())
                .planName(rs.getString("plan_name"))
                .lastPayment(rs.getDate("last_payment").toLocalDate())
                .nextPayment(rs.getDate("next_payment").toLocalDate())
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public void modify(DTOCompanyUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/modify.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getName());
            st.setString(2, dto.getAddress());
            st.setString(3, dto.getPhone());
            st.setString(4, dto.getEmail());
            st.setInt(5, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void modifyPlan(DTOCompanyUpdatePlan dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/modifyPlan.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getPlanId());
            st.setInt(2, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void modifyStatus(DTOCompanyUpdateStatus dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/modifyStatus.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getStatus().toString());
            st.setInt(2, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/company/delete.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException("Empresa no encontrada");
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

}
