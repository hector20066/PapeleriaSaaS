package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SupplierPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviders;
import com.hector.papeleriasaas.model.dto.DTOSupplier;
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
public class DAOProvidersImpl implements DAOProviders {

    public DAOProvidersImpl() {

    }

    @Override
    public void add(Connection connection, DTOSupplier dto) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getNit());
            st.setString(2, dto.getRSocial());
            st.setString(3, dto.getAddress());
            st.setString(4, dto.getPhone());
            st.setString(5, dto.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al agregar proveedor", e);
        }
    }

    @Override
    public Optional<DTOSupplier> findByName(String name) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/findByName.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOSupplier(rs));
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nombre", e);
        }
    }

    @Override
    public Optional<DTOSupplier> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/findById.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOSupplier(rs));
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nit", e);
        }
    }

    @Override
    public Optional<DTOSupplier> findById(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/findById.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOSupplier(rs));
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nit", e);
        }
    }

    @Override
    public List<DTOSupplier> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/supplier/findByAll.sql");
        List<DTOSupplier> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                DTOSupplier dto = getDTOSupplier(rs);
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al obtener todos los proveedores", e);
        }

        return list;
    }

    private DTOSupplier getDTOSupplier(ResultSet rs) throws SQLException {
        return new DTOSupplier.Builder()
                .nit(rs.getInt("nit"))
                .rSocial(rs.getString("rSocial"))
                .address(rs.getString("direccion"))
                .phone(rs.getString("telefono"))
                .email(rs.getString("correo"))
                .build();
    }

    @Override
    public void modify(Connection connection, DTOSupplier dto) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getRSocial());
            st.setString(2, dto.getAddress());
            st.setString(3, dto.getPhone());
            st.setString(4, dto.getEmail());
            st.setInt(5, dto.getNit());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("proveedor no existe con nit: " + dto.getNit());
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al modificar proveedor", e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/supplier/delete.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, id);
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("proveedor no existe con nit: " + id);
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al eliminar proveedor", e);
        }
    }

    @Override
    public int countRecords() throws DAOException {
        String sql = SQLRepository.get("sql/supplier/countRecords.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al contar proveedores", e);
        }
    }

}
