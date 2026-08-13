package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;

import java.util.List;

public interface DAOProviderProducts {

    void add(Integer providerId, Integer productId) throws DAOException;
    List<DTOProviderDetails> findProviderProducts(Integer productId);

}
