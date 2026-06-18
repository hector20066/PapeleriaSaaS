package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanyPayments;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOCompanyPaymentSimpleDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOCompanyPaymentsImpl implements DAOCompanyPayments {

    @Override
    public void add(DTOCompanyPaymentCreate dto) throws DAOException {

    }

    @Override
    public List<DTOCompanyPaymentDetails> findByCompany(int companyId) throws DAOException {
        return List.of();
    }

    @Override
    public Optional<DTOCompanyPaymentSimpleDetails> findById(int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public Optional<DTOCompanyPaymentSimpleDetails> findById(Connection connection, int id) throws DAOException {
        return Optional.empty();
    }

    @Override
    public List<DTOCompanyPaymentSimpleDetails> findByAll() throws DAOException {
        return List.of();
    }

}
