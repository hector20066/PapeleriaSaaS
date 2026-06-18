package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAODebts extends IListable<DTODebtDetails>,
        ICountable {

    List<DTODebtDetails> findByCustomer(String customer) throws DAOException;
    List<DTODebtDetails> findByStatus(PaymentStatus status) throws DAOException;

}
