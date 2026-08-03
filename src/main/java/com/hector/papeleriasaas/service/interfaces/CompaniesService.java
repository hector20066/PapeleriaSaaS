package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanySimpleDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Status;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CompaniesService {

    void registerCompanyInfo(String planName, DTOCompanyCreate dtoCompanyCreate, DTOUserCreate dtoUserCreate) throws SQLException, DAOException;
    DTOCompanySimpleDetails findById(int id) throws DAOException;
    DTOCompanySimpleDetails findByName(String name) throws DAOException;
    DTOCompanySimpleDetails findByNit(int nit) throws DAOException;
    DTOCompanyDetails findCompanyInfo(int id) throws DAOException;
    DTOCompanyDetails findCompanyInfo(Connection connection, int id) throws DAOException;
    List<DTOCompanySimpleDetails> findByAll() throws DAOException;
    void modify(String name, String address, String phone, String email, int id) throws DAOException;
    void modifyPlan(int planId, int id) throws DAOException;
    void modifyStatus(Status status, int id) throws DAOException;
    void delete(int id) throws DAOException;

}
