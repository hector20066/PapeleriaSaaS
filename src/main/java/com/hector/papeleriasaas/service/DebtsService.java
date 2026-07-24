package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.exception.dao.DAOException;
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
public class DebtsService {

    private final DAODebts daoDebts;
    private int companyId = 0; // Se debe obtener

    public DebtsService(DAODebts daoDebts) {
        this.daoDebts = daoDebts;
    }

    public List<DTODebtDetails> findByCustomer(String customer) throws DAOException {
        return daoDebts.findByCustomer(customer, companyId);
    }

    public List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException {
        return daoDebts.findByStatus(status, 1);
    }

    public Optional<DTODebtDetails> findById(int id) throws DAOException {
        return daoDebts.findById(id, companyId);
    }

    public Optional<DTODebtDetails> findById(Connection connection, int id) throws DAOException {
        return daoDebts.findById(connection, id, companyId);
    }

    public List<DTODebtDetails> findByAll() throws DAOException {
        return daoDebts.findByAll(companyId);
    }

    public int countRecords() throws DAOException {
        return daoDebts.countRecords(companyId);
    }

}
