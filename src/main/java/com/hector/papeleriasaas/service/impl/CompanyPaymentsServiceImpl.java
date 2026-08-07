package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanyPayments;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.service.interfaces.CompanyPaymentsService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CompanyPaymentsServiceImpl implements CompanyPaymentsService {

    private final DAOCompanyPayments daoCompanyPayments;

    public CompanyPaymentsServiceImpl(DAOCompanyPayments daoCompanyPayments) {
        this.daoCompanyPayments = daoCompanyPayments;
    }

    @Override
    public void add(int companyId, int planId, BigDecimal amountPaid, String paymentReference, String observations, PaymentStatus paymentStatus) throws DAOException {
        LocalDate paymentDate = LocalDate.now();
        LocalDate expirationDate = paymentDate.plusMonths(1);

        DTOPaymentCreate dto = new DTOPaymentCreate.Builder()
                .companyId(companyId)
                .planId(planId)
                .amountPaid(amountPaid)
                .paymentDate(paymentDate)
                .expirationDate(expirationDate)
                .paymentReference(paymentReference)
                .observations(observations)
                .paymentStatus(paymentStatus)
                .build();

        daoCompanyPayments.add(dto);
    }

    @Override
    public DTOPaymentDetails findById(int id, int companyId) throws DAOException {
        Optional<DTOPaymentDetails> dtoPaymentDetailsOptional = daoCompanyPayments.findById(id, companyId);

        if (dtoPaymentDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoPaymentDetailsOptional.get();
    }

    @Override
    public List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws DAOException {
        return daoCompanyPayments.findByCompany(companyId);
    }

    @Override
    public List<DTOPaymentSimpleDetails> findByAll() throws DAOException {
        return daoCompanyPayments.findByAll();
    }

}
