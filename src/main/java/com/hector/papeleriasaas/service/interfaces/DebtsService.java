package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface DebtsService {

    List<DTODebtDetails> findByCustomer(String customer) throws DAOException;
    List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException;
    Optional<DTODebtDetails> findById(int id) throws DAOException;
    Optional<DTODebtDetails> findById(Connection connection, int id) throws DAOException;
    List<DTODebtDetails> findByAll() throws DAOException;
    int countRecords() throws DAOException;

}
