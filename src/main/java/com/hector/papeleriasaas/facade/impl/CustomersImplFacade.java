package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.CustomersFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOCustomersImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCustomers;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CustomersImplFacade implements CustomersFacade {

    private final DAOCustomers daoCustomers;

    public CustomersImplFacade() {
        this.daoCustomers = new DAOCustomersImpl();
    }

    @Override
    public void add(Connection connection, int id, String name, String phone) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .identification(id)
                .name(name)
                .phone(phone)
                .build();

        daoCustomers.add(connection, dto);
    }

    @Override
    public Optional<DTOCustomerDetails> findByName(String name) throws DAOException {
        return daoCustomers.findByName(name);
    }

    @Override
    public Optional<DTOCustomerDetails> findById(int id) throws DAOException {
        return daoCustomers.findById(id);
    }

    @Override
    public Optional<DTOCustomerDetails> findById(Connection connection, int id) throws DAOException {
        return daoCustomers.findById(connection, id);
    }

    @Override
    public List<DTOCustomerDetails> findByAll() throws DAOException {
        return daoCustomers.findByAll();
    }

    @Override
    public void modify(Connection connection, String phone, int id) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .phone(phone)
                .identification(id)
                .build();

        daoCustomers.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoCustomers.countRecords();
    }

}
