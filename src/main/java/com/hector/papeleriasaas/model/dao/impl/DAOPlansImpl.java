package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPlans;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanUpdate;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOPlansImpl implements DAOPlans {

    @Override
    public int add(DTOPlanCreate dto) throws DAOException {
        return 0;
    }

    @Override
    public Optional<DTOPlanCreate> findById(int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOPlanCreate> findById(Connection connection, int id, int companyId) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOPlanCreate> findByAll(int companyId) throws DAOException {
        return List.of();
    }

    @Override
    public void modify(Connection connection, DTOPlanUpdate dto) throws DAOException {

    }

    @Override
    public void delete(int id, int companyId) throws DAOException {

    }

}
