package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.SupplierNotFoundException;
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
    public void registerProviderPurchase(Connection connection, int nit, String rSocial, String address, String phone,
                                         String email) throws DAOException {
        DTOProvider dto = new DTOProvider.Builder()
                .nit(nit)
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .companyId(companyId)
                .build();

        if (daoProviders.findById(connection, nit, companyId).isEmpty()) {
            daoProviders.add(connection, dto);
        } else {
            daoProviders.modify(connection, dto);
        }
    }

    @Override
    public DTOProvider findByName(String name) throws DAOException {
        Optional<DTOProvider> dtoProvider = daoProviders.findByName(name, companyId);

        if (dtoProvider.isEmpty()) {
            throw new SupplierNotFoundException(name);
        }

        return dtoProvider.get();
    }

    @Override
    public DTOProvider findById(int id) throws DAOException {
        Optional<DTOProvider> dtoProvider = daoProviders.findById(id, companyId);

        if (dtoProvider.isEmpty()) {
            throw new SupplierNotFoundException(String.valueOf(id));
        }

        return dtoProvider.get();
    }

    @Override
    public List<DTOProvider> findByAll() throws DAOException {
        return daoProviders.findByAll(companyId);
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
