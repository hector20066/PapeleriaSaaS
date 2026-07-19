package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.CustomerPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCustomers;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
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
public class DAOCustomersImpl implements DAOCustomers {

    private final ConnectionProvider connectionProvider;

    public DAOCustomersImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(Connection connection, DTOCustomerCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/customer/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getIdentification());
            st.setString(2, dto.getName());
            st.setString(3, dto.getPhone());
            st.setString(4, dto.getEmail());
            st.setInt(5, dto.getCompanyId());

            return st.executeUpdate();
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al agregar cliente", e);
        }
    }

    @Override
    public Optional<DTOCustomerDetails> findByName(String name, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findByName.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOCustomer(rs));
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al buscar cliente por el nombre: " + name, e);
        }
    }

    @Override
    public Optional<DTOCustomerDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOCustomer(rs));
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al buscar el cliente por id: " + id, e);
        }
    }

    @Override
    public Optional<DTOCustomerDetails> findById(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findById.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOCustomer(rs));
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al buscar el cliente por id: " + id, e);
        }
    }

    @Override
    public List<DTOCustomerDetails> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findByAllCompany.sql");
        List<DTOCustomerDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOCustomer(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al obtener todos los clientes", e);
        }
    }

    private DTOCustomerDetails getDTOCustomer(ResultSet rs) throws SQLException {
        return new DTOCustomerDetails.Builder()
                .identification(rs.getInt("identification"))
                .name(rs.getString("name"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .build();
    }

    @Override
    public void modify(Connection connection, DTOCustomerCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/customer/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getName());
            st.setString(2, dto.getPhone());
            st.setString(3, dto.getEmail());
            st.setInt(4, dto.getIdentification());
            st.setInt(5, dto.getCompanyId());

            if (st.executeUpdate() == 0) {
                throw new SQLException("cliente no existe con id: " + dto.getIdentification());
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al modificar cliente", e);
        }
    }

    @Override
    public void delete(int id, int companyId) throws DAOException {

    }

    @Override
    public int countRecords(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/customer/countRecords.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return 0;
                }

                return rs.getInt("total_records");
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al contar clientes", e);
        }
    }

}
