package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.CustomersFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOCustomersImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCustomers;
import com.hector.papeleriasaas.model.dto.DTOCustomer;

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
        DTOCustomer dto = new DTOCustomer();
        dto.setIdentification(id);
        dto.setName(name);
        dto.setPhone(phone);

        daoCustomers.add(connection, dto);
    }

    @Override
    public Optional<DTOCustomer> findByName(String name) throws DAOException {
        return daoCustomers.findByName(name);
    }

    @Override
    public Optional<DTOCustomer> findById(int id) throws DAOException {
        return daoCustomers.findById(id);
    }

    @Override
    public Optional<DTOCustomer> findById(Connection connection, int id) throws DAOException {
        return daoCustomers.findById(connection, id);
    }

    @Override
    public List<DTOCustomer> findByAll() throws DAOException {
        return daoCustomers.findByAll();
    }

    @Override
    public void modify(Connection connection, String phone, int id) throws DAOException {
        DTOCustomer dto = new DTOCustomer();
        dto.setPhone(phone);
        dto.setIdentification(id);

        daoCustomers.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoCustomers.countRecords();
    }

}
