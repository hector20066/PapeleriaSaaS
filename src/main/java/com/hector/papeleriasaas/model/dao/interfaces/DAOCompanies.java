package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DAOCompanies {

    int add(DTOCompanyCreate dto) throws DAOException;
    Optional<DTOCompanySimpleDetails> findByName(String name) throws DAOException;
    Optional<DTOCompanySimpleDetails> findByNit(int nit) throws DAOException;
    Optional<DTOCompanyDetails> findCompanyInfo(int id) throws DAOException;
    Optional<DTOCompanyDetails> findCompanyInfo(Connection connection, int id) throws DAOException;
    Optional<DTOCompanySimpleDetails> findById(int id) throws DAOException;
    List<DTOCompanySimpleDetails> findByAll() throws DAOException;
    void modify(DTOCompanyUpdate dto) throws DAOException;
    void modifyPlan(DTOCompanyUpdatePlan dto) throws DAOException;
    void modifyStatus(DTOCompanyUpdateStatus dto) throws DAOException;
    void delete(int id) throws DAOException;

}
