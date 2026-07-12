package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.ProductPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProducts;
import com.hector.papeleriasaas.model.dto.product.*;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DAOProductsImpl implements DAOProducts {

    private final List<DTOProductDetails> productList;

    public DAOProductsImpl() {
        this.productList = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public List<DTOProductDetails> getProductList() {
        return this.productList;
    }

    @Override
    public int add(Connection connection, DTOProductCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/product/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, dto.getDescription());
            st.setBigDecimal(2, dto.getPurchasePrice());
            st.setBigDecimal(3, dto.getSalePrice());
            st.setInt(4, dto.getCompanyId());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating product failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al agregar producto", e);
        }
    }

    @Override
    public Optional<DTOProductDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/product/findById.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOProductDetails(rs));
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al buscar producto por id: " + id, e);
        }
    }

    @Override
    public Optional<DTOProductDetails> findById(Connection connection, int id) throws DAOException {
        String sql = SQLRepository.get("sql/product/findById.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOProductDetails(rs));
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al buscar producto por id: " + id, e);
        }
    }

    @Override
    public Optional<DTOProductDetails> findByName(String product) throws DAOException {
        String sql = SQLRepository.get("sql/product/findByName.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, product);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOProductDetails(rs));
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al buscar producto por nombre: " + product, e);
        }
    }

    @Override
    public Optional<DTOProductDetails> findByName(Connection connection, String product) throws DAOException {
        String sql = SQLRepository.get("sql/product/findByName.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, product);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(getDTOProductDetails(rs));
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al buscar producto por nombre: " + product, e);
        }
    }

    @Override
    public List<DTOProductDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/product/findByAll.sql");
        List<DTOProductDetails> list = new ArrayList<>();

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            this.productList.clear();

            while (rs.next()) {
                DTOProductDetails dto = getDTOProductDetails(rs);
                this.productList.add(dto);
                list.add(dto);
            }

            return list;
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al obtener todos los productos", e);
        }
    }

    private DTOProductDetails getDTOProductDetails(ResultSet rs) throws SQLException {
        return new DTOProductDetails.Builder()
                .id(rs.getInt("id"))
                .description(rs.getString("description"))
                .amount(rs.getInt("available_quantity"))
                .purchasePrice(rs.getBigDecimal("purchase_price"))
                .salePrice(rs.getBigDecimal("sale_price"))
                .provider(rs.getString("provider"))
                .earnings(rs.getBigDecimal("earnings"))
                .build();
    }

    @Override
    public Optional<DTOProductBestSelling> bestSelling() throws DAOException {
        String sql = SQLRepository.get("sql/product/bestSelling.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return Optional.empty();
            }

            DTOProductBestSelling dto = new DTOProductBestSelling.Builder()
                    .id(rs.getInt("id"))
                    .description(rs.getString("description"))
                    .quantitySold(rs.getInt("quantity_sold"))
                    .build();

            return Optional.of(dto);
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al obtener el producto más vendido", e);
        }
    }

    @Override
    public void modify(DTOProductUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/product/modify.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                modifyData(st, dto);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al modificar producto", e);
        }
    }

    @Override
    public void modify(Connection connection, DTOProductUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/product/modify.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            modifyData(st, dto);
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al modificar producto", e);
        }
    }

    private void modifyData(PreparedStatement st, DTOProductUpdate dto) throws SQLException {
        st.setString(1, dto.getDescription());
        st.setInt(2, dto.getAmount());
        st.setBigDecimal(3, dto.getSalePrice());
        st.setBigDecimal(4, dto.getPurchasePrice());
        st.setInt(5, dto.getId());
        st.setInt(6, dto.getCompanyId());

        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("producto no existe con id: " + dto.getId());
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/product/delete.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection()) {
            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.setInt(1, id);
                // st.setInt(2, id_company);
                int affectedRows = st.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("producto no existe con id: " + id);
                }

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al eliminar producto", e);
        }
    }

    @Override
    public int countRecords() throws DAOException {
        String sql = SQLRepository.get("sql/product/countRecords.sql");

        try (Connection connection = DataBasePool.getInstance().getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (!rs.next()) {
                return 0;
            }

            return rs.getInt("total_records");
        } catch (SQLException e) {
            throw new ProductPersistenceException("Error al contar productos", e);
        }
    }

}
