package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOCompanyPayments {

    void add(DTOPaymentCreate dto) throws DAOException;
    Optional<DTOPaymentDetails> findById(int id, int companyId) throws DAOException;
    List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws DAOException;
    List<DTOPaymentSimpleDetails> findByAll() throws DAOException;

}
