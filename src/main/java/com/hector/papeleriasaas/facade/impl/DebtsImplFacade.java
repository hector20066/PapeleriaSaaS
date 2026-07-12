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
    private int companyId = 0; // Se debe obtener

    public DebtsImplFacade(DAODebts daoDebts) {
        this.daoDebts = daoDebts;
    }

    @Override
    public List<DTODebtDetails> findByCustomer(String customer) throws DAOException {
        return daoDebts.findByCustomer(customer, companyId);
    }

    @Override
    public List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException {
        return daoDebts.findByStatus(status, 1);
    }

    @Override
    public Optional<DTODebtDetails> findById(int id) throws DAOException {
        return daoDebts.findById(id, companyId);
    }

    @Override
    public Optional<DTODebtDetails> findById(Connection connection, int id) throws DAOException {
        return daoDebts.findById(connection, id, companyId);
    }

    @Override
    public List<DTODebtDetails> findByAll() throws DAOException {
        return daoDebts.findByAll(companyId);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoDebts.countRecords(companyId);
    }

}
