package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviderProducts;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;
import com.hector.papeleriasaas.service.interfaces.ProviderProductsService;

import java.sql.Connection;
import java.util.Optional;

public class ProviderProductsServiceImpl implements ProviderProductsService {

    private final DAOProviderProducts daoProviderProducts;
    private final Session session;

    public ProviderProductsServiceImpl(DAOProviderProducts daoProviderProducts, Session session) {
        this.daoProviderProducts = daoProviderProducts;
        this.session = session;
    }

    @Override
    public void add(Connection connection, Integer providerId, Integer productId) throws DAOException {
        daoProviderProducts.add(connection, providerId, productId);
    }

    @Override
    public Optional<DTOProviderDetails> findProviderProducts(Integer productId) throws DAOException {
        return daoProviderProducts.findProviderProducts(productId, session.getCompanyId());
    }

}
