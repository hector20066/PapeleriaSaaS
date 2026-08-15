package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;

import java.sql.Connection;
import java.util.Optional;

public interface ProviderProductsService {

    void add(Connection connection, Integer providerId, Integer productId) throws ServiceException;
    Optional<DTOProviderDetails> findProviderProducts(Integer productId) throws ServiceException;

}
