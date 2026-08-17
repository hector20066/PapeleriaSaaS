package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CustomerService {

    int add(Connection connection, int id, String name, String phone, String email) throws ServiceException;
    DTOCustomerDetails findByName(String name) throws ServiceException;
    DTOCustomerDetails findById(int id) throws ServiceException;
    DTOCustomerDetails findById(Connection connection, int id) throws ServiceException;
    List<DTOCustomerDetails> findByAll() throws ServiceException;
    void modify(Connection connection, String name, String phone, String email, int id) throws ServiceException;
    int countRecords() throws ServiceException;

}
