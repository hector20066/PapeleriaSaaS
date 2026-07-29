package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAORememberTokens;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenCreate;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.Optional;

public class DAORememberTokensImpl implements DAORememberTokens {

    private final ConnectionProvider connectionProvider;

    public DAORememberTokensImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(DTORememberTokenCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/rememberToken/insert.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getToken());
            st.setInt(2, dto.getUserId());

            if (dto.getCompanyId() != null) {
                st.setInt(3, dto.getCompanyId());
            } else {
                st.setNull(3, Types.INTEGER);
            }

            st.setTimestamp(4, Timestamp.valueOf(dto.getExpiresAt()));

            if (dto.getDeviceName() != null) {
                st.setString(5, dto.getDeviceName());
            } else {
                st.setNull(5, Types.VARCHAR);
            }

            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error al registrar el token", e);
        }
    }

    @Override
    public Optional<DTORememberTokenDetails> findByToken(String token) throws DAOException {
        String sql = SQLRepository.get("sql/rememberToken/findByToken.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, token);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTORememberToken(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error al buscar el token", e);
        }
    }

    @Override
    public void updateLastUsedAt(String token) throws DAOException {
        String sql = SQLRepository.get("sql/rememberToken/updateLastUsed.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, token);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error al actualizar la fecha de uso del token", e);
        }
    }

    @Override
    public void deleteByToken(String token) throws DAOException {

        String sql = SQLRepository.get("sql/rememberToken/deleteByToken.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, token);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error al eliminar el token", e);
        }
    }

    @Override
    public void deleteByUser(int userId) throws DAOException {
        String sql = SQLRepository.get("sql/rememberToken/deleteByUser.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error al eliminar los tokens del usuario", e);
        }
    }

    @Override
    public void deleteExpiredTokens() throws DAOException {
        String sql = SQLRepository.get("sql/rememberToken/deleteExpired.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Error al eliminar tokens expirados", e);
        }
    }

    private DTORememberTokenDetails getDTORememberToken(ResultSet rs) throws SQLException {
        return new DTORememberTokenDetails.Builder()
                .id(rs.getInt("id"))
                .token(rs.getString("token"))
                .userId(rs.getInt("user_id"))
                .companyId((Integer) rs.getObject("company_id"))
                .createAt(rs.getTimestamp("created_at").toLocalDateTime())
                .expiresAt(rs.getTimestamp("expires_at").toLocalDateTime())
                .lastUsedAt(rs.getTimestamp("last_used_at") != null
                        ? rs.getTimestamp("last_used_at").toLocalDateTime()
                        : null)
                .deviceName(rs.getString("device_name"))
                .build();
    }

}
