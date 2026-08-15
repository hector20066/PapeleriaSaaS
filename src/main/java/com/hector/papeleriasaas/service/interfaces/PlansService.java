package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.enums.BillingCycle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface PlansService {

    int add(String name, BigDecimal price, String description, BillingCycle billingCycle) throws ServiceException;
    DTOPlanDetails findById(int id) throws ServiceException;
    DTOPlanDetails findByName(String planName) throws ServiceException;
    DTOPlanDetails findByName(Connection connection, String planName) throws ServiceException;
    List<DTOPlanDetails> findByAll() throws ServiceException;
    void modify(String name, BigDecimal price, String description, BillingCycle billingCycle, int id) throws ServiceException;
    void delete(int id) throws ServiceException;

}
