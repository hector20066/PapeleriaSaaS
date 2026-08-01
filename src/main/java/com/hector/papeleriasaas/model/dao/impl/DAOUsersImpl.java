package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.auth.DTOAuthentication;
import com.hector.papeleriasaas.auth.DTOAuthenticationRoot;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.user.*;
import com.hector.papeleriasaas.model.enums.Roles;
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
public class DAOUsersImpl implements DAOUsers {

    private final ConnectionProvider connectionProvider;

    public DAOUsersImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(DTOUserCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/user/insert.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            return registerUser(st, dto);
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public int add(Connection connection, DTOUserCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/user/insert.sql");

        try (PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            return registerUser(st, dto);
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private int registerUser(PreparedStatement st, DTOUserCreate dto) throws SQLException {
        st.setInt(1, dto.getIdentification());
        st.setString(2, dto.getName());
        st.setString(3, dto.getUser());
        st.setString(4, dto.getPasswd());
        st.setString(5, String.valueOf(dto.getRole()));
        st.setString(6, String.valueOf(dto.getStatus()));
        st.setInt(7, dto.getCompanyId());

        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    @Override
    public Optional<DTOUserDetails> findByIdentification(int identification) throws DAOException {
        String sql = SQLRepository.get("sql/user/findByIdentification.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, identification);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOUserDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOUserDetails> findById(int id) throws DAOException {
        String sql = SQLRepository.get("sql/user/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOUserDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOUserDetails> findByUser(String user) throws DAOException {
        String sql = SQLRepository.get("sql/user/findByUser.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOUserDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public List<DTOUserDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/user/findByAll.sql");
        List<DTOUserDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(getDTOUserDetails(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public List<DTOUserDetails> findByAll(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/user/findByCompanies.sql");
        List<DTOUserDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOUserDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOUserDetails getDTOUserDetails(ResultSet rs) throws SQLException {
        return new DTOUserDetails.Builder()
                .id(rs.getInt("id"))
                .identification(rs.getInt("identification"))
                .name(rs.getString("name"))
                .user(rs.getString("user"))
                .role(Roles.valueOf(rs.getString("role")))
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public Optional<DTOAuthentication> findByNitAndUser(String user, int companyNit) throws DAOException {
        String sql = SQLRepository.get("sql/user/findByNitAndUser.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyNit);
            st.setString(2, user);

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(new DTOAuthentication.Builder()
                        .userId(rs.getInt("id"))
                        .user(rs.getString("user"))
                        .passwd(rs.getString("password"))
                        .userStatus(Status.valueOf(rs.getString("status")))
                        .companyId(rs.getInt("id_company"))
                        .companyStatus(Status.valueOf(rs.getString("company_status")))
                        .build());
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOAuthenticationRoot> findAuthenticationRoot(String user) throws DAOException {
        String sql = SQLRepository.get("sql/user/findAuthenticationRoot.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user);
            st.setString(2, String.valueOf(Roles.ROOT));

            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                return Optional.of(new DTOAuthenticationRoot.Builder()
                        .userId(rs.getInt("id"))
                        .user(rs.getString("user"))
                        .passwd(rs.getString("password"))
                        .status(Status.valueOf(rs.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void modify(DTOUserUpdate dto) throws DAOException {
        String sql = SQLRepository.get("sql/user/modify.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getIdentification());
            st.setString(2, dto.getName());
            st.setString(3, dto.getUser());
            st.setString(4, String.valueOf(dto.getRoles()));
            st.setString(5, String.valueOf(dto.getStatus()));
            st.setInt(6, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void modifyPassword(DTOUserUpdatePasswd dto) throws DAOException {
        String sql = SQLRepository.get("sql/user/modifyPassword.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getPasswd());
            st.setInt(2, dto.getId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void modifyPasswordCompany(DTOUserUpdatePasswdCompany dto) throws DAOException {
        String sql = SQLRepository.get("sql/user/modifyPasswordCompany.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, dto.getPasswd());
            st.setInt(2, dto.getId());
            st.setInt(3, dto.getCompanyId());

            if (st.executeUpdate() == 0) {
                throw new DataNotFoundException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = SQLRepository.get("sql/user/delete.sql");

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
