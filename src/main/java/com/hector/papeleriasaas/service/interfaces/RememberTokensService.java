package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;

public interface RememberTokensService {

    String createToken(Integer userId, Integer companyId) throws DAOException;
    DTORememberTokenDetails findByToken(String token) throws DAOException;
    void updateLastUsedAt(String token) throws DAOException;
    void deleteByToken(String token) throws DAOException;
    void deleteByUser(Integer userId) throws DAOException;
    void deleteExpiredTokens() throws DAOException;

}
