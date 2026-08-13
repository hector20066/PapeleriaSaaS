package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProviderProducts;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;

import java.util.List;

public class DAOProviderProductsImpl implements DAOProviderProducts {

    @Override
    public void add(Integer providerId, Integer productId) throws DAOException {

    }

    @Override
    public List<DTOProviderDetails> findProviderProducts(Integer productId) {
        return List.of();
    }
    
}
