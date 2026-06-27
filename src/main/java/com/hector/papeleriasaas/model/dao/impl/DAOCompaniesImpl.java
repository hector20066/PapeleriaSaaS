package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyUpdate;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOCompaniesImpl implements DAOCompanies {

    @Override
    public int add(DTOCompanyCreate dto) throws DAOException {
        String sql = SQLRepository.get("");
        return 0;
    }

    @Override
    public Optional<DTOCompanyDetails> findByName(String name) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findByNit(int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findById(int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyDetails> findById(Connection connection, int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOCompanyDetails> findByAll() throws DAOException {
        return List.of();
    }

    @Override
    public void modify(Connection connection, DTOCompanyUpdate dto) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

}
