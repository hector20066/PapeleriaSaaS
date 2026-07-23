package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanyPayments;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCreate;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentDetails;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentSimpleDetails;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.utils.SQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOCompanyPaymentsImpl implements DAOCompanyPayments {

    private final ConnectionProvider connectionProvider;

    public DAOCompanyPaymentsImpl(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void add(DTOPaymentCreate dto) throws DAOException {
        String sql = SQLRepository.get("sql/companyPayment/insert.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, dto.getCompanyId());
            st.setInt(2, dto.getPlanId());
            st.setBigDecimal(3, dto.getAmountPaid());
            st.setDate(4, Date.valueOf(dto.getPaymentDate()));
            st.setDate(5, Date.valueOf(dto.getExpirationDate()));
            st.setString(6, dto.getPaymentReference());
            st.setString(7, dto.getObservations());
            st.setString(8, String.valueOf(dto.getPaymentStatus()));

            if (st.executeUpdate() == 0) {
                throw new PersistenceException();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public Optional<DTOPaymentDetails> findById(int id, int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/companyPayment/adminFilters/findById.sql");

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next()
                        ? Optional.of(getDTOPaymentDetails(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOPaymentDetails getDTOPaymentDetails(ResultSet rs) throws SQLException {
        return new DTOPaymentDetails.Builder()
                .id(rs.getInt("id"))
                .companyName(rs.getString("company_name"))
                .companyId(rs.getInt("company_id"))
                .planName(rs.getString("plan_name"))
                .planId(rs.getInt("plan_id"))
                .amountPaid(rs.getBigDecimal("amount_paid"))
                .paymentDate(rs.getDate("payment_date").toLocalDate())
                .expirationDate(rs.getDate("expiration_date").toLocalDate())
                .paymentReference(rs.getString("payment_reference"))
                .observations(rs.getString("observations"))
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public List<DTOPaymentCompanyDetails> findByCompany(int companyId) throws DAOException {
        String sql = SQLRepository.get("sql/companyPayment/findByCompany.sql");
        List<DTOPaymentCompanyDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, companyId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(getDTOPaymentCompanyDetails(rs));
                }

                return list;
            }
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOPaymentCompanyDetails getDTOPaymentCompanyDetails(ResultSet rs) throws SQLException {
        return new DTOPaymentCompanyDetails.Builder()
                .planName(rs.getString("plan_name"))
                .amountPaid(rs.getBigDecimal("amount_paid"))
                .paymentDate(rs.getDate("payment_date").toLocalDate())
                .expirationDate(rs.getDate("expiration_date").toLocalDate())
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

    @Override
    public List<DTOPaymentSimpleDetails> findByAll() throws DAOException {
        String sql = SQLRepository.get("sql/companyPayment/adminFilters/findByAll.sql");
        List<DTOPaymentSimpleDetails> list = new ArrayList<>();

        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                list.add(getDTOPaymentSimpleDetails(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new PersistenceException();
        }
    }

    private DTOPaymentSimpleDetails getDTOPaymentSimpleDetails(ResultSet rs) throws SQLException {
        return new DTOPaymentSimpleDetails.Builder()
                .id(rs.getInt("id"))
                .companyName(rs.getString("company_name"))
                .planName(rs.getString("plan_name"))
                .amountPaid(rs.getBigDecimal("amount_paid"))
                .paymentDate(rs.getDate("payment_date").toLocalDate())
                .expirationDate(rs.getDate("expiration_date").toLocalDate())
                .status(Status.valueOf(rs.getString("status")))
                .build();
    }

}
