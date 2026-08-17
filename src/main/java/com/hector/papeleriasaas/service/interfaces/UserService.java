package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.auth.DTOAuthentication;
import com.hector.papeleriasaas.auth.DTOAuthenticationRoot;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface UserService {

    int add(Integer identification, String name, String user, String passwd, Roles role, Status status) throws ServiceException;
    int add(Integer identification, String name, String user, String passwd, Roles role, Status status, Integer companyId) throws ServiceException;
    int add(Connection connection, Integer identification, String name, String user, String passwd, Roles role, Status status,
            Integer companyId) throws ServiceException;

    DTOUserDetails findByIdentification(Integer identification) throws ServiceException;
    DTOUserDetails findById(Integer id) throws ServiceException;
    DTOUserDetails findByUser(String user) throws ServiceException;
    List<DTOUserDetails> findByAll() throws ServiceException;
    List<DTOUserDetails> findByAllCompany() throws ServiceException;
    Optional<DTOAuthentication> findByNitAndUser(String user, int companyNit) throws ServiceException;
    Optional<DTOAuthenticationRoot> findAuthenticationRoot(String user) throws ServiceException;
    void modify(int id, int identification, String name, String user, Roles role, Status status) throws ServiceException;
    void modifyPassword(String passwd, int id) throws ServiceException;
    void modifyPasswordCompany(String passwd, int id) throws ServiceException;
    void delete(int id) throws ServiceException;

}
