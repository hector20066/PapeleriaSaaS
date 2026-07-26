package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.enums.BillingCycle;
import com.hector.papeleriasaas.service.interfaces.PlansService;

import java.math.BigDecimal;
import java.util.List;

public class PlansServiceImpl implements PlansService {

    @Override
    public int add(String name, BigDecimal price, String description, BillingCycle billingCycle) throws DAOException {
        return 0;
    }

    @Override
    public DTOPlanDetails findById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<DTOPlanDetails> findByAll() throws DAOException {
        return List.of();
    }

    @Override
    public void modify(String name, BigDecimal price, String description, BillingCycle billingCycle, int id) throws DAOException {

    }

    @Override
    public void delete(int id) throws DAOException {

    }

}
