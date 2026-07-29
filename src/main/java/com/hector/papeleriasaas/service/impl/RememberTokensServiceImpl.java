package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAORememberTokens;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenCreate;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;
import com.hector.papeleriasaas.service.interfaces.RememberTokensService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RememberTokensServiceImpl implements RememberTokensService {

    private final DAORememberTokens daoRememberTokens;

    public RememberTokensServiceImpl(DAORememberTokens daoRememberTokens) {
        this.daoRememberTokens = daoRememberTokens;
    }

    @Override
    public String createToken(Integer userId, Integer companyId) throws DAOException {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusMonths(6);
        String deviceName = "";

        DTORememberTokenCreate dto = new DTORememberTokenCreate.Builder()
                .token(token)
                .userId(userId)
                .companyId(companyId)
                .expiresAt(expiresAt)
                .deviceName(deviceName)
                .build();

        daoRememberTokens.add(dto);

        return token;
    }

    @Override
    public DTORememberTokenDetails findByToken(String token) throws DAOException {
        Optional<DTORememberTokenDetails> dtoRememberOptional = daoRememberTokens.findByToken(token);

        if (dtoRememberOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoRememberOptional.get();
    }

    @Override
    public void updateLastUsedAt(String token) throws DAOException {
        daoRememberTokens.updateLastUsedAt(token);
    }

    @Override
    public void deleteByToken(String token) throws DAOException {
        daoRememberTokens.deleteByToken(token);
    }

    @Override
    public void deleteByUser(Integer userId) throws DAOException {
        daoRememberTokens.deleteByUser(userId);
    }

    @Override
    public void deleteExpiredTokens() throws DAOException {
        daoRememberTokens.deleteExpiredTokens();
    }

}
