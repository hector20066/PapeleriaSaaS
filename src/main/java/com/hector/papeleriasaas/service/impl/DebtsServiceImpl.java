package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
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
    private final Session session;

    public DebtsServiceImpl(DAODebts daoDebts, Session session) {
        this.daoDebts = daoDebts;
        this.session = session;
    }

    @Override
    public List<DTODebtDetails> findByCustomer(String customer) throws ServiceException {
        try {
            List<DTODebtDetails> list = daoDebts.findByCustomer(customer, session.getCompanyId());

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException(customer);
            }

            return list;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTODebtDetails> findByStatus(PaymentStatus status) throws ServiceException {
        try {
            return daoDebts.findByStatus(status, 1);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTODebtDetails findById(int id) throws ServiceException {
        try {
            Optional<DTODebtDetails> debtDetailsOptional = daoDebts.findById(id, session.getCompanyId());

            if (debtDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return debtDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTODebtDetails findById(Connection connection, int id) throws ServiceException {
        try {
            Optional<DTODebtDetails> debtDetailsOptional = daoDebts.findById(connection, id, session.getCompanyId());

            if (debtDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return debtDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTODebtDetails> findByAll() throws ServiceException {
        try {
            return daoDebts.findByAll(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int countRecords() throws ServiceException {
        try {
            return daoDebts.countRecords(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
