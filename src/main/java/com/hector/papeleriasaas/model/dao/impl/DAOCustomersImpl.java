package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.CustomerPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCustomers;
import com.hector.papeleriasaas.model.dto.DTOCustomer;
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

    public DAOCustomersImpl() {

    }

    @Override
    public void add(Connection connection, DTOCustomer dto) throws DAOException {
        String sql = SQLRepository.get("sql/customer/add.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getId());
            st.setString(2, dto.getName());
            st.setString(3, dto.getPhone());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al agregar cliente", e);
        }
    }

    @Override
    public Optional<DTOCustomer> findByName(String name) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findByName.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);

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
    public Optional<DTOCustomer> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/customer/findById.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
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
    public Optional<DTOCustomer> findById(Connection connection, int id) throws DAOException {
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
    public List<DTOCustomer> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/customer/findByAll.sql");
        List<DTOCustomer> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(getDTOCustomer(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al obtener todos los clientes", e);
        }
    }

    private DTOCustomer getDTOCustomer(ResultSet rs) throws SQLException {
        DTOCustomer dto = new DTOCustomer();
        dto.setId(rs.getInt("id"));
        dto.setName(rs.getString("nombre"));
        dto.setPhone(rs.getString("telefono"));

        return dto;
    }

    @Override
    public void modify(Connection connection, DTOCustomer dto) throws DAOException {
        String sql = SQLRepository.get("sql/customer/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getPhone());
            st.setInt(2, dto.getId());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("cliente no existe con id: " + dto.getId());
            }
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al modificar cliente", e);
        }
    }

    @Override
    public int countRecords() throws DAOException {
        String sql = SQLRepository.get("sql/customer/countRecords.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error al contar clientes", e);
        }
    }

}
