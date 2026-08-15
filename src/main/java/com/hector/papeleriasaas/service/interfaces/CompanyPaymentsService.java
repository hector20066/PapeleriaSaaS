package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface CompanyPaymentsService {

    void add(int companyId, int planId, BigDecimal amountPaid, String paymentReference, String observations, PaymentStatus paymentStatus) throws ServiceException;
    DTOPaymentDetails findById(int id, int companyId) throws ServiceException;
    List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws ServiceException;
    List<DTOPaymentSimpleDetails> findByAll() throws ServiceException;

}
