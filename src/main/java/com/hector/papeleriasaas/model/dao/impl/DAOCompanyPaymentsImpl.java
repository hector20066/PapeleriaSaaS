package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanyPayments;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DAOCompanyPaymentsImpl implements DAOCompanyPayments {

    private final ConnectionProvider connectionProvider;

    public DAOCompanyPaymentsImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(DTOPaymentCreate dto) throws DAOException {

    }

    @Override
    public Optional<DTOPaymentDetails> findById(int id, int companyId) throws DAOException {
        return Optional.empty();
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
