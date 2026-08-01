package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.auth.DTOAuthentication;
import com.hector.papeleriasaas.auth.DTOAuthenticationRoot;
import com.hector.papeleriasaas.exception.dao.DAOException;
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

    int add(Integer identification, String name, String user, String passwd, Roles role, Status status) throws DAOException;
    int add(Connection connection, Integer identification, String name, String user, String passwd, Roles role, Status status) throws DAOException;
    DTOUserDetails findByIdentification(Integer identification) throws DAOException;
    DTOUserDetails findById(Integer id) throws DAOException;
    DTOUserDetails findByUser(String user) throws DAOException;
    List<DTOUserDetails> findByAll() throws DAOException;
    List<DTOUserDetails> findByAllCompany() throws DAOException;
    Optional<DTOAuthentication> findByNitAndUser(String user, int companyNit) throws DAOException;
    Optional<DTOAuthenticationRoot> findAuthenticationRoot(String user) throws DAOException;
    void modify(int id, int identification, String name, String user, Roles role, Status status) throws DAOException;
    void modifyPassword(String passwd, int id) throws DAOException;
    void modifyPasswordCompany(String passwd, int id) throws DAOException;
    void delete(int id) throws DAOException;

}
