package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.ICountable;
import com.hector.papeleriasaas.model.dao.interfaces.IListable;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.StatusDebt;

import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DebtsFacade extends IListable<DTODebtDetails>,
        ICountable {

    List<DTODebtDetails> findByCustomer(String customer) throws DAOException;
    List<DTODebtDetails> findByStatus(StatusDebt status) throws DAOException;

}
