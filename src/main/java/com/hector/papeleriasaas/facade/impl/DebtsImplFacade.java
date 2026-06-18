package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.DebtsFacade;
import com.hector.papeleriasaas.model.dao.impl.DAODebtsImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAODebts;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DebtsImplFacade implements DebtsFacade {

    private final DAODebts daoDebts;

    public DebtsImplFacade() {
        this.daoDebts = new DAODebtsImpl();
    }

    @Override
    public List<DTODebtDetails> findByCustomer(String customer) throws DAOException {
        return daoDebts.findByCustomer(customer);
    }

    @Override
    public List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException {
        return daoDebts.findByStatus(status);
    }

    @Override
    public Optional<DTODebtDetails> findById(int id) throws DAOException {
        return daoDebts.findById(id);
    }

    @Override
    public Optional<DTODebtDetails> findById(Connection connection, int id) throws DAOException {
        return daoDebts.findById(connection, id);
    }

    @Override
    public List<DTODebtDetails> findByAll() throws DAOException {
        return daoDebts.findByAll();
    }

    @Override
    public int countRecords() throws DAOException {
        return daoDebts.countRecords();
    }

}
