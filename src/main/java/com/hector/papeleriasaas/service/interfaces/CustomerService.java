package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    void add(Connection connection, int id, String name, String phone) throws DAOException;
    Optional<DTOCustomerDetails> findByName(String name) throws DAOException;
    Optional<DTOCustomerDetails> findById(int id) throws DAOException;
    Optional<DTOCustomerDetails> findById(Connection connection, int id) throws DAOException;
    List<DTOCustomerDetails> findByAll() throws DAOException;
    void modify(Connection connection, String phone, int id) throws DAOException;
    int countRecords() throws DAOException;

}
