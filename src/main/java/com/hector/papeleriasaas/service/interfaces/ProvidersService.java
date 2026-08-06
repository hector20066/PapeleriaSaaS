package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProvidersService {

    int registerProviderPurchase(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;
    DTOProvider findByName(String name) throws DAOException;
    DTOProvider findById(int id) throws DAOException;
    List<DTOProvider> findByAll() throws DAOException;
    void delete(int id) throws DAOException;
    int countRecords() throws DAOException;

}
