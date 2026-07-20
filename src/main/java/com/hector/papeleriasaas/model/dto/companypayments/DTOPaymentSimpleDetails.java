package com.hector.papeleriasaas.model.dto.companypayments;

import com.hector.papeleriasaas.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTOPaymentSimpleDetails {

    private Integer id;
    private String companyName;
    private String planName;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private Status status;

    public DTOPaymentSimpleDetails() {

    }

    public DTOPaymentSimpleDetails(Builder builder) {
        this.id = builder.id;
        this.companyName = builder.companyName;
        this.planName = builder.planName;
        this.amountPaid = builder.amountPaid;
        this.paymentDate = builder.paymentDate;
        this.expirationDate = builder.expirationDate;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPlanName() {
        return planName;
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

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private Integer id;
        private String companyName;
        private String planName;
        private BigDecimal amountPaid;
        private LocalDate paymentDate;
        private LocalDate expirationDate;
        private Status status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder planName(String planName) {
            this.planName = planName;
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

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOPaymentSimpleDetails build() {
            return new DTOPaymentSimpleDetails(this);
        }

    }

}
