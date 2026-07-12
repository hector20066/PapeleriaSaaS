package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyUpdate;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAOCompanies extends IModifiable<DTOCompanyUpdate> {

    int add(DTOCompanyCreate dto) throws DAOException;
    Optional<DTOCompanyDetails> findByName(String name) throws DAOException;
    Optional<DTOCompanyDetails> findByNit(int id) throws DAOException;
    Optional<DTOCompanyDetails> findById(int id) throws DAOException;
    Optional<DTOCompanyDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOCompanyDetails> findByAll() throws DAOException;
    void delete(int id) throws DAOException;

}
