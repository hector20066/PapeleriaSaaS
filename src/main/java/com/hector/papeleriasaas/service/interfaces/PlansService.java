package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
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

    int add(String name, BigDecimal price, String description, BillingCycle billingCycle) throws DAOException;
    DTOPlanDetails findById(int id) throws DAOException;
    DTOPlanDetails findByName(String planName) throws DAOException;
    DTOPlanDetails findByName(Connection connection, String planName) throws DAOException;
    List<DTOPlanDetails> findByAll() throws DAOException;
    void modify(String name, BigDecimal price, String description, BillingCycle billingCycle, int id) throws DAOException;
    void delete(int id) throws DAOException;

}
