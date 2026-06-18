package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.ProvidersFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOProvidersImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviders;
import com.hector.papeleriasaas.model.dto.DTOSupplier;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProvidersImplFacade implements ProvidersFacade {

    private final DAOProviders daoSuppliers;

    public ProvidersImplFacade() {
        this.daoSuppliers = new DAOProvidersImpl();
    }

    @Override
    public void add(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException {
        DTOSupplier dto = new DTOSupplier.Builder()
                .nit(nit)
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .build();

        daoSuppliers.add(connection, dto);
    }

    @Override
    public Optional<DTOSupplier> findByName(String name) throws DAOException {
        return daoSuppliers.findByName(name);
    }

    @Override
    public Optional<DTOSupplier> findById(int id) throws DAOException {
        return daoSuppliers.findById(id);
    }

    @Override
    public Optional<DTOSupplier> findById(Connection connection, int id) throws DAOException {
        return daoSuppliers.findById(connection, id);
    }

    @Override
    public List<DTOSupplier> findByAll() throws DAOException {
        return daoSuppliers.findByAll();
    }

    @Override
    public void modify(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException {
        DTOSupplier dto = new DTOSupplier.Builder()
                .nit(nit)
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .build();

        daoSuppliers.modify(connection, dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoSuppliers.delete(id);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoSuppliers.countRecords();
    }

}
