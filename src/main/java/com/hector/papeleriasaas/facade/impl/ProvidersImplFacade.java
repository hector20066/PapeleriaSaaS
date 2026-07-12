package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.ProvidersFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOProvidersImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviders;
import com.hector.papeleriasaas.model.dto.DTOProvider;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProvidersImplFacade implements ProvidersFacade {

    private final DAOProviders daoSuppliers;
    private int companyId = 0;

    public ProvidersImplFacade() {
        this.daoSuppliers = new DAOProvidersImpl();
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

        daoSuppliers.add(connection, dto);
    }

    @Override
    public Optional<DTOProvider> findByName(String name) throws DAOException {
        return daoSuppliers.findByName(name, companyId);
    }

    @Override
    public Optional<DTOProvider> findById(int id) throws DAOException {
        return daoSuppliers.findById(id, companyId);
    }

    @Override
    public Optional<DTOProvider> findById(Connection connection, int id) throws DAOException {
        return daoSuppliers.findById(connection, id, companyId);
    }

    @Override
    public List<DTOProvider> findByAll() throws DAOException {
        return daoSuppliers.findByAll(companyId);
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

        daoSuppliers.modify(connection, dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoSuppliers.delete(id, companyId);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoSuppliers.countRecords(companyId);
    }

}
