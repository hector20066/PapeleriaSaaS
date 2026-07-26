package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface CompanyPaymentsService {

    void add(int companyId, int planId, BigDecimal amountPaid, String paymentReference, String observations, PaymentStatus paymentStatus) throws DAOException;
    DTOPaymentDetails findById(int id, int companyId) throws DAOException;
    List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws DAOException;
    List<DTOPaymentSimpleDetails> findByAll() throws DAOException;

}
