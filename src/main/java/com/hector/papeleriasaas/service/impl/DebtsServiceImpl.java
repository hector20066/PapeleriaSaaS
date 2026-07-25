package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.DebtNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAODebts;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.service.interfaces.DebtsService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DebtsServiceImpl implements DebtsService {

    private final DAODebts daoDebts;
    private int companyId = 0; // Se debe obtener

    public DebtsServiceImpl(DAODebts daoDebts) {
        this.daoDebts = daoDebts;
    }

    @Override
    public List<DTODebtDetails> findByCustomer(String customer) throws DAOException {
        List<DTODebtDetails> list = daoDebts.findByCustomer(customer, companyId);

        if (list.isEmpty()) {
            throw new DebtNotFoundException(customer);
        }

        return list;
    }

    @Override
    public List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException {
        return daoDebts.findByStatus(status, 1);
    }

    @Override
    public DTODebtDetails findById(int id) throws DAOException {
        Optional<DTODebtDetails> debtDetailsOptional = daoDebts.findById(id, companyId);

        if (debtDetailsOptional.isEmpty()) {
            throw new DebtNotFoundException(String.valueOf(id));
        }

        return debtDetailsOptional.get();
    }

    @Override
    public DTODebtDetails findById(Connection connection, int id) throws DAOException {
        Optional<DTODebtDetails> debtDetailsOptional = daoDebts.findById(connection, id, companyId);

        if (debtDetailsOptional.isEmpty()) {
            throw new DebtNotFoundException(String.valueOf(id));
        }

        return debtDetailsOptional.get();
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
