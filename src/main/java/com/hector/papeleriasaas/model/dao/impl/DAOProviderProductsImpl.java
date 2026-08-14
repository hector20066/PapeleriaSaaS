package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviderProducts;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DAOProviderProductsImpl implements DAOProviderProducts {

    private final ConnectionProvider connectionProvider;

    public DAOProviderProductsImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(Connection connection, Integer providerId, Integer productId) throws DAOException {
        String sql = SQLRepository.get("sql/providerProducts/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, providerId);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Ha ocurrido un error al registrar la información");
        }
    }

    @Override
    public Optional<DTOProviderDetails> findProviderProducts(Integer productId, Integer companyId) throws DAOException {
        String sql = SQLRepository.get("sql/providerProducts/findProviderProduct.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            st.setInt(2, companyId);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOProviderDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException("Ha ocurrido un error durante la búsqueda");
        }
    }

    private DTOProviderDetails getDTOProviderDetails(ResultSet rs) throws SQLException {
        return new DTOProviderDetails.Builder()
                .id(rs.getInt("id"))
                .nit(rs.getInt("nit"))
                .rSocial(rs.getString("rSocial"))
                .address(rs.getString("address"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .build();
    }

}
