package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProvidersService {

    int registerProviderPurchase(Connection connection, int nit, String rSocial, String address, String phone,
                                 String email) throws ServiceException;

    DTOProvider findByName(String name) throws ServiceException;
    DTOProvider findById(int id) throws ServiceException;
    List<DTOProvider> findByAll() throws ServiceException;
    void delete(int id) throws ServiceException;
    int countRecords() throws ServiceException;

}
