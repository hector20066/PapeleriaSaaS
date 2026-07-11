package com.hector.papeleriasaas.model.dto;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTODebtDetails {

    private Integer id;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private Integer numSale;
    private LocalDate date;
    private BigDecimal totalSale;
    private BigDecimal totalPaid;
    private BigDecimal totalDebt;
    private PaymentStatus status;

    public DTODebtDetails() {

    }

    public DTODebtDetails(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.customerName = builder.customerName;
        this.customerPhone = builder.customerPhone;
        this.numSale = builder.numSale;
        this.date = builder.date;
        this.totalSale = builder.totalSale;
        this.totalPaid = builder.totalPaid;
        this.totalDebt = builder.totalDebt;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public Integer getNumSale() {
        return numSale;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getStatusValue() {
        return getStatus().getValue();
    }

    public static class Builder {

        private Integer id;
        private Integer customerId;
        private String customerName;
        private String customerPhone;
        private Integer numSale;
        private LocalDate date;
        private BigDecimal totalSale;
        private BigDecimal totalPaid;
        private BigDecimal totalDebt;
        private PaymentStatus status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder customerId(Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
            return this;
        }

        public Builder numSale(Integer numSale) {
            this.numSale = numSale;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder totalSale(BigDecimal totalSale) {
            this.totalSale = totalSale;
            return this;
        }

        public Builder totalPaid(BigDecimal totalPaid) {
            this.totalPaid = totalPaid;
            return this;
        }

        public Builder totalDebt(BigDecimal totalDebt) {
            this.totalDebt = totalDebt;
            return this;
        }

        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public DTODebtDetails build() {
            return new DTODebtDetails(this);
        }

    }

}
