package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;

import java.sql.Connection;
import java.util.List;

public interface DAOProviderProducts {

    void add(Connection connection, Integer providerId, Integer productId) throws DAOException;
    List<DTOProviderDetails> findProviderProducts(Integer productId, Integer companyId) throws DAOException;

}
