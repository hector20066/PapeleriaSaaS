package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DebtsService {

    List<DTODebtDetails> findByCustomer(String customer) throws ServiceException;
    List<DTODebtDetails> findByStatus(PaymentStatus status) throws ServiceException;
    DTODebtDetails findById(int id) throws ServiceException;
    DTODebtDetails findById(Connection connection, int id) throws ServiceException;
    List<DTODebtDetails> findByAll() throws ServiceException;
    int countRecords() throws ServiceException;

}
