package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyUpdate;
import com.hector.papeleriasaas.utils.SQLRepository;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DAOCompaniesImpl implements DAOCompanies {

    private final ConnectionProvider connectionProvider;

    public DAOCompaniesImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(DTOCompanyCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/insert.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
    public Optional<DTOCompanyDetails> findByName(String name) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findByNit(int nit) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findById(int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findById(Connection connection, int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOCompanyDetails> findByAll() throws DAOException {
        return List.of();
    }

    @Override
    public void modify(DTOCompanyUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/company/modify.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

        } catch (SQLException e) {

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
