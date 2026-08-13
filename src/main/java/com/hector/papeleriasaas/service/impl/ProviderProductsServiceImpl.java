package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviderProducts;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;
import com.hector.papeleriasaas.service.interfaces.ProviderProductsService;

import java.sql.Connection;
import java.util.List;

public class ProviderProductsServiceImpl implements ProviderProductsService {

    private final DAOProviderProducts daoProviderProducts;

    public ProviderProductsServiceImpl(DAOProviderProducts daoProviderProducts) {
        this.daoProviderProducts = daoProviderProducts;
    }

    @Override
    public void add(Connection connection, Integer providerId, Integer productId) throws DAOException {
        daoProviderProducts.add(connection, providerId, productId);
    }

    @Override
    public List<DTOProviderDetails> findProviderProducts(Integer productId) throws DAOException {
        return daoProviderProducts.findProviderProducts(productId);
    }

}
