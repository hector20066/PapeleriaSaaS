package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.SupplierPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviders;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
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

    private final ConnectionProvider connectionProvider;

    public DAOProvidersImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(Connection connection, DTOProvider dto) throws DAOException {
        String sql = SQLRepository.get("sql/provider/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getNit());
            st.setString(2, dto.getRSocial());
            st.setString(3, dto.getAddress());
            st.setString(4, dto.getPhone());
            st.setString(5, dto.getEmail());
            st.setInt(6, dto.getCompanyId());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating provider failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating provider failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al agregar proveedor", e);
        }
    }

    @Override
    public Optional<DTOProvider> findByName(String name, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/findByName.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, name);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOSupplier(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nombre", e);
        }
    }

    @Override
    public Optional<DTOProvider> findById(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOSupplier(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nit", e);
        }
    }

    @Override
    public Optional<DTOProvider> findById(Connection connection, int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/findById.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOSupplier(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al buscar proveedor por nit", e);
        }
    }

    @Override
    public List<DTOProvider> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/findByAll.sql");
        List<DTOProvider> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    DTOProvider dto = getDTOSupplier(rs);
                    list.add(dto);
                }

                return list;
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al obtener todos los proveedores", e);
        }
    }

    private DTOProvider getDTOSupplier(ResultSet rs) throws SQLException {
        return new DTOProvider.Builder()
                .id(rs.getInt("id"))
                .nit(rs.getInt("nit"))
                .rSocial(rs.getString("rSocial"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .build();
    }

    @Override
    public void modify(Connection connection, DTOProvider dto) throws DAOException {
        String sql = SQLRepository.get("sql/provider/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getRSocial());
            st.setString(2, dto.getAddress());
            st.setString(3, dto.getPhone());
            st.setString(4, dto.getEmail());
            st.setInt(5, dto.getNit());
            st.setInt(6, dto.getCompanyId());

            if (st.executeUpdate() == 0) {
                throw new SQLException("proveedor no existe con nit: " + dto.getNit());
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al modificar proveedor", e);
        }
    }

    @Override
    public void delete(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/delete.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyId);

            if (st.executeUpdate() == 0) {
                throw new SQLException("proveedor no existe con nit: " + id);
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al eliminar proveedor", e);
        }
    }

    @Override
    public int countRecords(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/provider/countRecords.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);
            
            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? rs.getInt("total_records")
                        : 0;
            }
        } catch (SQLException e) {
            throw new SupplierPersistenceException("Error al contar proveedores", e);
        }
    }

}
