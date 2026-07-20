package com.hector.papeleriasaas.model.dto.companypayments;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

public class DTOPaymentCreate {

    private Integer companyId;
    private Integer planId;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private String paymentReference;
    private String observations;
    private PaymentStatus paymentStatus;

    public DTOPaymentCreate() {

    }

    public DTOPaymentCreate(Builder builder) {
        this.companyId = builder.companyId;
        this.planId = builder.planId;
        this.amountPaid = builder.amountPaid;
        this.paymentDate = builder.paymentDate;
        this.expirationDate = builder.expirationDate;
        this.paymentReference = builder.paymentReference;
        this.observations = builder.observations;
        this.paymentStatus = builder.paymentStatus;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public static class Builder {

        private Integer companyId;
        private Integer planId;
        private BigDecimal amountPaid;
        private LocalDate paymentDate;
        private LocalDate expirationDate;
        private String paymentReference;
        private String observations;
        private PaymentStatus paymentStatus; 
        
        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
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

        public Builder paymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public DTOPaymentCreate build() {
            return new DTOPaymentCreate(this);
        }

    }

}
