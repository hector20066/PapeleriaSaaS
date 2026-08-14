package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;

import java.sql.Connection;
import java.util.Optional;

public interface ProviderProductsService {

    void add(Connection connection, Integer providerId, Integer productId) throws DAOException;
    Optional<DTOProviderDetails> findProviderProducts(Integer productId) throws DAOException;

}
