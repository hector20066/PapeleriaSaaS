package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentSimpleDetails;

import java.util.List;

public interface DAOCompanyPayments extends IListable<DTOCompanyPaymentSimpleDetails> {

    void add(DTOCompanyPaymentCreate dto) throws DAOException;
    List<DTOCompanyPaymentDetails> findByCompany(int companyId) throws DAOException;
    List<DTOCompanyPaymentSimpleDetails> findByAll() throws DAOException;

}
