package com.hector.papeleriasaas.model.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.hector.papeleriasaas.auth.DTOUserLogin;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.user.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOUsers {

    int add(DTOUserCreate dto) throws DAOException;
    Optional<DTOUserDetails> findByIdentification(int identification) throws DAOException;
    Optional<DTOUserDetails> findById(int id) throws DAOException;
    Optional<DTOUserDetails> findByUser(String user) throws DAOException;
    List<DTOUserDetails> findByAll() throws DAOException;
    List<DTOUserDetails> findByAll(int companyId) throws DAOException;
    Optional<DTOUserLogin> validateCredentials(String user, int companyNit) throws DAOException;
    void modify(DTOUserUpdate dto) throws DAOException;
    void modifyPassword(DTOUserUpdatePasswd dto) throws DAOException;
    void modifyPasswordCompany(DTOUserUpdatePasswdCompany dto) throws DAOException;
    void delete(int id) throws DAOException;

}
