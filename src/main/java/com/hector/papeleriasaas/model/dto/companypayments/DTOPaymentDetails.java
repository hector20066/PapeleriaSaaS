package com.hector.papeleriasaas.model.dto.companypayments;

import com.hector.papeleriasaas.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTOPaymentDetails {

    private Integer id;
    private String companyName;
    private Integer companyId;
    private String planName;
    private Integer planId;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private String paymentReference;
    private String observations;
    private Status status;

    public DTOPaymentDetails() {

    }

    public DTOPaymentDetails(Builder builder) {
        this.id = builder.id;
        this.companyName = builder.companyName;
        this.companyId = builder.companyId;
        this.planName = builder.planName;
        this.planId = builder.planId;
        this.amountPaid = builder.amountPaid;
        this.paymentDate = builder.paymentDate;
        this.expirationDate = builder.expirationDate;
        this.paymentReference = builder.paymentReference;
        this.observations = builder.observations;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getPlanName() {
        return planName;
    }

    public Integer getPlanId() {
        return planId;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public String getObservations() {
        return observations;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private Integer id;
        private String companyName;
        private Integer companyId;
        private String planName;
        private Integer planId;
        private BigDecimal amountPaid;
        private LocalDate paymentDate;
        private LocalDate expirationDate;
        private String paymentReference;
        private String observations;
        private Status status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder planName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder planId(Integer planId) {
            this.planId = planId;
            return this;
        }

        public Builder amountPaid(BigDecimal amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        public Builder paymentDate(LocalDate paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder expirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder paymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public Builder observations(String observations) {
            this.observations = observations;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOPaymentDetails build() {
            return new DTOPaymentDetails(this);
        }

    }

}
