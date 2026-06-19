package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserUpdate;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOUsersImpl implements DAOUsers {

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
    public Optional<DTOUserDetails> findById(int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOUserDetails> findById(Connection connection, int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOUserDetails> findByAll() throws DAOException {
        return List.of();
    }

    @Override
    public void modify(Connection connection, DTOUserUpdate dto) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

}
