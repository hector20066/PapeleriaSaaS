package com.hector.papeleriasaas.model.dto.companypayments;

import com.hector.papeleriasaas.model.enums.Status;

import java.time.LocalDate;

public class DTOPaymentCompanyDetails {

    private String planName;
    private Integer amountPaid;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private Status status;

    public DTOPaymentCompanyDetails() {

    }

    public DTOPaymentCompanyDetails(Builder builder) {
        this.planName = builder.planName;
        this.amountPaid = builder.amountPaid;
        this.paymentDate = builder.paymentDate;
        this.expirationDate = builder.expirationDate;
        this.status = builder.status;
    }

    public String getPlanName() {
        return planName;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private String planName;
        private Integer amountPaid;
        private LocalDate paymentDate;
        private LocalDate expirationDate;
        private Status status;

        public Builder planName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder amountPaid(Integer amountPaid) {
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

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOPaymentCompanyDetails build() {
            return new DTOPaymentCompanyDetails(this);
        }

    }

}
