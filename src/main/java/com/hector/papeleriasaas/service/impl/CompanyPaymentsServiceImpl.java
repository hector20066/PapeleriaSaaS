package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
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
    public void add(int companyId, int planId, BigDecimal amountPaid, String paymentReference, String observations,
                    PaymentStatus paymentStatus) throws ServiceException {
        try {
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
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOPaymentDetails findById(int id, int companyId) throws ServiceException {
        try {
            Optional<DTOPaymentDetails> dtoPaymentDetailsOptional = daoCompanyPayments.findById(id, companyId);

            if (dtoPaymentDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoPaymentDetailsOptional.get();
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws ServiceException {
        try {
            return daoCompanyPayments.findByCompany(companyId);
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOPaymentSimpleDetails> findByAll() throws ServiceException {
        try {
            return daoCompanyPayments.findByAll();
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
