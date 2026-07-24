package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
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
    private int companyId = 0;

    public ProvidersServiceImpl(DAOProviders daoProviders) {
        this.daoProviders = daoProviders;
    }

    @Override
    public void add(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException {
        DTOProvider dto = new DTOProvider.Builder()
                .nit(nit)
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .companyId(companyId)
                .build();

        daoProviders.add(connection, dto);
    }

    @Override
    public Optional<DTOProvider> findByName(String name) throws DAOException {
        return daoProviders.findByName(name, companyId);
    }

    @Override
    public Optional<DTOProvider> findById(int id) throws DAOException {
        return daoProviders.findById(id, companyId);
    }

    @Override
    public Optional<DTOProvider> findById(Connection connection, int id) throws DAOException {
        return daoProviders.findById(connection, id, companyId);
    }

    @Override
    public List<DTOProvider> findByAll() throws DAOException {
        return daoProviders.findByAll(companyId);
    }

    @Override
    public void modify(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException {
        DTOProvider dto = new DTOProvider.Builder()
                .nit(nit)
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .companyId(companyId)
                .build();

        daoProviders.modify(connection, dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoProviders.delete(id, companyId);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoProviders.countRecords(companyId);
    }

}
