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
    private int companyId = 0; // Se debe obtener

    public CustomersImplFacade(DAOCustomers daoCustomers) {
        this.daoCustomers = daoCustomers;
    }

    @Override
    public void add(Connection connection, int id, String name, String phone) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .identification(id)
                .name(name)
                .phone(phone)
                .companyId(companyId)
                .build();

        daoCustomers.add(connection, dto);
    }

    @Override
    public Optional<DTOCustomerDetails> findByName(String name) throws DAOException {
        return daoCustomers.findByName(name, companyId);
    }

    @Override
    public Optional<DTOCustomerDetails> findById(int id) throws DAOException {
        return daoCustomers.findById(id, companyId);
    }

    @Override
    public Optional<DTOCustomerDetails> findById(Connection connection, int id) throws DAOException {
        return daoCustomers.findById(connection, id, companyId);
    }

    @Override
    public List<DTOCustomerDetails> findByAll() throws DAOException {
        return daoCustomers.findByAll(companyId);
    }

    @Override
    public void modify(Connection connection, String phone, int id) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .phone(phone)
                .identification(id)
                .companyId(companyId)
                .build();

        daoCustomers.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoCustomers.countRecords(companyId);
    }

}
