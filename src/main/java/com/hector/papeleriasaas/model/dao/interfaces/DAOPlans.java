package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOPlans {

    int add(DTOPlanCreate dto) throws DAOException;
    Optional<DTOPlanDetails> findById(int id) throws DAOException;
    Optional<DTOPlanDetails> findByName(String planName) throws DAOException;
    Optional<DTOPlanDetails> findByName(Connection connection, String planName) throws DAOException;
    List<DTOPlanDetails> findByAll() throws DAOException;
    void modify(DTOPlanDetails dto) throws DAOException;
    void delete(int id) throws DAOException;

}
