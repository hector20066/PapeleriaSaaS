package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.user.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOUsersImpl implements DAOUsers {

    private ConnectionProvider connectionProvider;

    public DAOUsersImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(DTOUserCreate dto) throws DAOException {
        return 0;
    }

    @Override
    public Optional<DTOUserDetails> findByIdentification(int identification) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOUserDetails> findByUser(String user) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOUserDetails> validateCredentials(String user, String passwd) throws DAOException {
        return Optional.empty();
    }

    @Override
    public void modify(DTOUserUpdate dto) throws DAOException {

    }

    @Override
    public void modifyPassword(DTOUserUpdatePasswd dto) throws DAOException {

    }

    @Override
    public void modifyPasswordCompany(DTOUserUpdatePasswdCompany dto) throws DAOException {

    }

    @Override
    public Optional<DTOUserDetails> findById(int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOUserDetails> findById(Connection connection, int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOUserDetails> findByAll(int companyId) throws DAOException {
        return List.of();
    }

    @Override
    public void delete(int id, int companyId) throws DAOException {

    }

}
