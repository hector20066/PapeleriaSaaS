package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPlans;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.enums.BillingCycle;
import com.hector.papeleriasaas.service.interfaces.PlansService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PlansServiceImpl implements PlansService {

    private final DAOPlans daoPlans;

    public PlansServiceImpl(DAOPlans daoPlans) {
        this.daoPlans = daoPlans;
    }

    @Override
    public int add(String name, BigDecimal price, String description, BillingCycle billingCycle) throws DAOException {
        DTOPlanCreate dto = new DTOPlanCreate();
        dto.setName(name);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setBillingCycle(billingCycle);

        return daoPlans.add(dto);
    }

    @Override
    public DTOPlanDetails findById(int id) throws DAOException {
        Optional<DTOPlanDetails> dtoPlanDetailsOptional = daoPlans.findById(id);

        if (dtoPlanDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoPlanDetailsOptional.get();
    }

    @Override
    public List<DTOPlanDetails> findByAll() throws DAOException {
        List<DTOPlanDetails> list = daoPlans.findByAll();

        if (list.isEmpty()) {
            throw new DataNotFoundException();
        }

        return list;
    }

    @Override
    public void modify(String name, BigDecimal price, String description, BillingCycle billingCycle, int id) throws DAOException {
        DTOPlanDetails dto = new DTOPlanDetails.Builder()
                .name(name)
                .price(price)
                .description(description)
                .billingCycle(billingCycle)
                .id(id)
                .build();

        daoPlans.modify(dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoPlans.delete(id);
    }

}
