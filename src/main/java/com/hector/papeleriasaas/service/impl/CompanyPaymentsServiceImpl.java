package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.service.interfaces.CompanyPaymentsService;

import java.math.BigDecimal;
import java.util.List;

public class CompanyPaymentsServiceImpl implements CompanyPaymentsService {

    @Override
    public void add(int companyId, int planId, BigDecimal amountPaid, String paymentReference, String observations, PaymentStatus paymentStatus) throws DAOException {

    }

    @Override
    public DTOPaymentDetails findById(int id, int companyId) throws DAOException {
        return null;
    }

    @Override
    public List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws DAOException {
        return List.of();
    }

    @Override
    public List<DTOPaymentSimpleDetails> findByAll() throws DAOException {
        return List.of();
    }

}
