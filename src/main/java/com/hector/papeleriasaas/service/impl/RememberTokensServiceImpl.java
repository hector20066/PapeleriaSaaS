package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAORememberTokens;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenCreate;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;
import com.hector.papeleriasaas.service.interfaces.RememberTokensService;
import com.hector.papeleriasaas.utils.UserConfig;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RememberTokensServiceImpl implements RememberTokensService {

    private final DAORememberTokens daoRememberTokens;

    public RememberTokensServiceImpl(DAORememberTokens daoRememberTokens) {
        this.daoRememberTokens = daoRememberTokens;
    }

    @Override
    public String createToken(Integer userId, Integer companyId) throws ServiceException {
        try {
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

            UserConfig.set("remember.enabled", "true");
            UserConfig.set("remember.token", token);

            return token;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTORememberTokenDetails findByToken(String token) throws ServiceException {
        try {
            Optional<DTORememberTokenDetails> dtoRememberOptional = daoRememberTokens.findByToken(token);

            if (dtoRememberOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoRememberOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void updateLastUsedAt(String token) throws ServiceException {
        try {
            daoRememberTokens.updateLastUsedAt(token);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void deleteByToken(String token) throws ServiceException {
        try {
            daoRememberTokens.deleteByToken(token);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void deleteByUser(Integer userId) throws ServiceException {
        try {
            daoRememberTokens.deleteByUser(userId);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void deleteExpiredTokens() throws ServiceException {
        try {
            daoRememberTokens.deleteExpiredTokens();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
