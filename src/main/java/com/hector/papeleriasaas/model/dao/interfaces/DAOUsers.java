package com.hector.papeleriasaas.model.dao.interfaces;

import java.util.Optional;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.user.*;

public interface DAOUsers extends IListable<DTOUserDetails>,
       IRemovable {

    int add(DTOUserCreate dto) throws DAOException;
    Optional<DTOUserDetails> findByIdentification(int identification) throws DAOException;
    Optional<DTOUserDetails> findByUser(String user) throws DAOException;
    Optional<DTOUserDetails> validateCredentials(String user, String passwd) throws DAOException;
    void modify(DTOUserUpdate dto) throws DAOException;
    void modifyPassword(DTOUserUpdatePasswd dto) throws DAOException;
    void modifyPasswordCompany(DTOUserUpdatePasswdCompany dto) throws DAOException;

}
