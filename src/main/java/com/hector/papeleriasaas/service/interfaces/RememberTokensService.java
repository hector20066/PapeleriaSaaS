package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;

public interface RememberTokensService {

    String createToken(Integer userId, Integer companyId) throws ServiceException;
    DTORememberTokenDetails findByToken(String token) throws ServiceException;
    void updateLastUsedAt(String token) throws ServiceException;
    void deleteByToken(String token) throws ServiceException;
    void deleteByUser(Integer userId) throws ServiceException;
    void deleteExpiredTokens() throws ServiceException;

}
