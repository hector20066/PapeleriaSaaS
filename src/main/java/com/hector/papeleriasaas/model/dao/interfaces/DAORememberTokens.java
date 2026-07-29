package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenCreate;

import java.util.Optional;

public interface DAORememberTokens {

    void add(DTORememberTokenCreate dto) throws DAOException;
    Optional<DTORememberTokenDetails> findByToken(String token) throws DAOException;
    void updateLastUsedAt(String token) throws DAOException;
    void deleteByToken(String token) throws DAOException;
    void deleteByUser(int userId) throws DAOException;
    void deleteExpiredTokens() throws DAOException;

}
