package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviders;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.service.interfaces.ProvidersService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProvidersServiceImpl implements ProvidersService {

    private final DAOProviders daoProviders;
    private final Session session;

    public ProvidersServiceImpl(DAOProviders daoProviders, Session session) {
        this.daoProviders = daoProviders;
        this.session = session;
    }

    @Override
    public int registerProviderPurchase(Connection connection, int nit, String rSocial, String address, String phone,
                                         String email) throws ServiceException {
        try {
            DTOProvider dto = new DTOProvider.Builder()
                    .nit(nit)
                    .rSocial(rSocial)
                    .address(address)
                    .phone(phone)
                    .email(email)
                    .companyId(session.getCompanyId())
                    .build();

            Optional<DTOProvider> dtoProvider = daoProviders.findById(connection, nit, session.getCompanyId());
            Integer providerId;

            if (dtoProvider.isEmpty()) {
                providerId = daoProviders.add(connection, dto);
            } else {
                dto.setId(dtoProvider.get().getId());
                daoProviders.modify(connection, dto);
                providerId = dtoProvider.get().getId();
            }

            return providerId;
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProvider findByName(String name) throws ServiceException {
        try {
            Optional<DTOProvider> dtoProvider = daoProviders.findByName(name, session.getCompanyId());

            if (dtoProvider.isEmpty()) {
                throw new ServiceDataNotFoundException(name);
            }

            return dtoProvider.get();
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProvider findById(int id) throws ServiceException {
        try {
            Optional<DTOProvider> dtoProvider = daoProviders.findById(id, session.getCompanyId());

            if (dtoProvider.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return dtoProvider.get();
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOProvider> findByAll() throws ServiceException {
        try {
            return daoProviders.findByAll(session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoProviders.delete(id, session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int countRecords() throws ServiceException {
        try {
            return daoProviders.countRecords(session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
