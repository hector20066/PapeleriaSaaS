package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanySimpleDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Status;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CompaniesService {

    void registerCompanyInfo(String planName, DTOCompanyCreate dtoCompanyCreate, DTOUserCreate dtoUserCreate) throws ServiceException;
    DTOCompanySimpleDetails findById(int id) throws ServiceException;
    DTOCompanySimpleDetails findByName(String name) throws ServiceException;
    DTOCompanySimpleDetails findByNit(int nit) throws ServiceException;
    DTOCompanyDetails findCompanyInfo(int id) throws ServiceException;
    DTOCompanyDetails findCompanyInfo(Connection connection, int id) throws ServiceException;
    List<DTOCompanySimpleDetails> findByAll() throws ServiceException;
    void modify(String name, String address, String phone, String email, int id) throws ServiceException;
    void modifyPlan(int planId, int id) throws ServiceException;
    void modifyStatus(Status status, int id) throws ServiceException;
    void delete(int id) throws ServiceException;

}
