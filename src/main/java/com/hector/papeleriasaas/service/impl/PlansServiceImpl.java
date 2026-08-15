package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPlans;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.enums.BillingCycle;
import com.hector.papeleriasaas.service.interfaces.PlansService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PlansServiceImpl implements PlansService {

    private final DAOPlans daoPlans;

    public PlansServiceImpl(DAOPlans daoPlans) {
        this.daoPlans = daoPlans;
    }

    @Override
    public int add(String name, BigDecimal price, String description, BillingCycle billingCycle) throws ServiceException {
        try {
            DTOPlanCreate dto = new DTOPlanCreate();
            dto.setName(name);
            dto.setPrice(price);
            dto.setDescription(description);
            dto.setBillingCycle(billingCycle);

            return daoPlans.add(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOPlanDetails findById(int id) throws ServiceException {
        try {
            Optional<DTOPlanDetails> dtoPlanDetailsOptional = daoPlans.findById(id);

            return dtoPlanDetailsOptional.orElse(null);


        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOPlanDetails findByName(String planName) throws ServiceException {
        try {
            Optional<DTOPlanDetails> dtoPlanDetailsOptional = daoPlans.findByName(planName);

            return dtoPlanDetailsOptional.orElse(null);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOPlanDetails findByName(Connection connection, String planName) throws ServiceException {
        try {
            Optional<DTOPlanDetails> dtoPlanDetailsOptional = daoPlans.findByName(connection, planName);

            return dtoPlanDetailsOptional.orElse(null);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOPlanDetails> findByAll() throws ServiceException {
        try {
            List<DTOPlanDetails> list = daoPlans.findByAll();

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return list;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(String name, BigDecimal price, String description, BillingCycle billingCycle, int id) throws ServiceException {
        try {
            DTOPlanDetails dto = new DTOPlanDetails.Builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .billingCycle(billingCycle)
                    .id(id)
                    .build();

            daoPlans.modify(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoPlans.delete(id);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
