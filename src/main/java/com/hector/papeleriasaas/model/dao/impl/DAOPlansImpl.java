package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPlans;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOPlansImpl implements DAOPlans {

    private final ConnectionProvider connectionProvider;

    public DAOPlansImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(DTOPlanCreate dto) throws DAOException {
        return 0;
    }

    @Override
    public Optional<DTOPlanDetails> findById(int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOPlanDetails> findById(Connection connection, int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOPlanDetails> findByAll(int companyId) throws DAOException {
        return List.of();
    }

    @Override
    public void modify(Connection connection, DTOPlanDetails dto) throws DAOException {

    }

    @Override
    public void delete(int id, int companyId) throws DAOException {

    }

}
