package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanySimpleDetails;
import com.hector.papeleriasaas.model.enums.Status;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CompaniesService {

    int add(Connection connection, Integer nit, String name, String address, String phone, String email, Status status, int planId) throws DAOException;
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
