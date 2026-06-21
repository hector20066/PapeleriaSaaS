package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

public class CompanyPayment {

    private Integer id;
    private Plan plan;
    private BigDecimal amountPaid;
    private LocalDate paymentDate;
    private LocalDate expirationDate;
    private String observations;
    private PaymentStatus status;

    public CompanyPayment() {

    }

    public CompanyPayment(Builder builder) {
        this.id = builder.id;
        this.plan = builder.plan;
        this.amountPaid = builder.amountPaid;
        this.paymentDate = builder.paymentDate;
        this.expirationDate = builder.expirationDate;
        this.observations = builder.observations;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public static class Builder {

        private Integer id;
        private Plan plan;
        private BigDecimal amountPaid;
        private LocalDate paymentDate;
        private LocalDate expirationDate;
        private String observations;
        private PaymentStatus status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder plan(Plan plan) {
            this.plan = plan;
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

        public Builder observations(String observations) {
            this.observations = observations;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public CompanyPayment build() {
            return new CompanyPayment(this);
        }

    }

}
