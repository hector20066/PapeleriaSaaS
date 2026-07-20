package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;

import java.util.List;

public interface DAOCompanyPayments extends IListable<DTOPaymentSimpleDetails> {

    void add(DTOPaymentCreate dto) throws DAOException;
    List<DTOPaymentDetails> findByCompany(int companyId) throws DAOException;
    List<DTOPaymentSimpleDetails> findByAll() throws DAOException;

}
