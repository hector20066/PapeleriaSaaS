package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
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
    public int add(Connection connection, int id, String name, String phone) throws ServiceException {
        try {
            DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                    .identification(id)
                    .name(name)
                    .phone(phone)
                    .companyId(session.getCompanyId())
                    .build();

            return daoCustomers.add(connection, dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCustomerDetails findByName(String name) throws ServiceException {
        try {
            Optional<DTOCustomerDetails> customerOptional = daoCustomers.findByName(name, session.getCompanyId());

            if (customerOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(name);
            }

            return customerOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCustomerDetails findById(int id) throws ServiceException {
        try {
            Optional<DTOCustomerDetails> customerOptional = daoCustomers.findById(id);

            if (customerOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return customerOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCustomerDetails findById(Connection connection, int id) throws ServiceException {
        try {
            Optional<DTOCustomerDetails> customerOptional = daoCustomers.findById(connection, id);

            return customerOptional.orElse(null);


        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOCustomerDetails> findByAll() throws ServiceException {
        try {
            return daoCustomers.findByAll(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(Connection connection, String phone, int id) throws ServiceException {
        try {
            DTOCustomerCreate dto = new DTOCustomerCreate.Builder()
                    .phone(phone)
                    .identification(id)
                    .companyId(session.getCompanyId())
                    .build();

            daoCustomers.modify(connection, dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int countRecords() throws ServiceException {
        try {
            return daoCustomers.countRecords(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
