package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.CustomerNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCustomers;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.service.interfaces.CustomerService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CustomerServiceImpl implements CustomerService {

    private final DAOCustomers daoCustomers;
    private final Session session;

    public CustomerServiceImpl(DAOCustomers daoCustomers, Session session) {
        this.daoCustomers = daoCustomers;
        this.session = session;
    }

    @Override
    public int add(Connection connection, int id, String name, String phone) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .identification(id)
                .name(name)
                .phone(phone)
                .companyId(session.getCompanyId())
                .build();

        return daoCustomers.add(connection, dto);
    }

    @Override
    public DTOCustomerDetails findByName(String name) throws DAOException {
        Optional<DTOCustomerDetails> customerOptional = daoCustomers.findByName(name, session.getCompanyId());

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(name);
        }

        return customerOptional.get();
    }

    @Override
    public DTOCustomerDetails findById(int id) throws DAOException {
        Optional<DTOCustomerDetails> customerOptional = daoCustomers.findById(id);

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(String.valueOf(id));
        }

        return customerOptional.get();
    }

    @Override
    public DTOCustomerDetails findById(Connection connection, int id) throws DAOException {
        Optional<DTOCustomerDetails> customerOptional = daoCustomers.findById(connection, id);

        return customerOptional.orElse(null);

    }

    @Override
    public List<DTOCustomerDetails> findByAll() throws DAOException {
        return daoCustomers.findByAll(session.getCompanyId());
    }

    @Override
    public void modify(Connection connection, String phone, int id) throws DAOException {
        DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                .phone(phone)
                .identification(id)
                .companyId(session.getCompanyId())
                .build();

        daoCustomers.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoCustomers.countRecords(session.getCompanyId());
    }

}
