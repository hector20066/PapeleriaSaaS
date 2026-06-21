package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Sale {

    private Integer id;
    private Integer numSale;
    private Customer customer;
    private LocalDate date;
    private BigDecimal totalPaid;
    private BigDecimal totalSale;
    private List<SaleDetail> saleDetails;
    private Invoice invoice;
    private Debt debt;

    public Sale() {
        this.saleDetails = new ArrayList<>();
    }

    public Sale(Builder builder) {
        this();
        this.id = builder.id;
        this.numSale = builder.numSale;
        this.customer = builder.customer;
        this.date = builder.date;
        this.totalPaid = builder.totalPaid;
        this.totalSale = builder.totalSale;
        this.invoice = builder.invoice;
        this.debt = builder.debt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumSale() {
        return numSale;
    }

    public void setNumSale(Integer numSale) {
        this.numSale = numSale;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Debt getDebt() {
        return debt;
    }

    public void setDebt(Debt debt) {
        this.debt = debt;
    }

    public Sale setSaleDetails(SaleDetail saleDetail) {
        this.saleDetails.add(saleDetail);
        return this;
    }

    public static class Builder {

        private Integer id;
        private Integer numSale;
        private Customer customer;
        private LocalDate date;
        private BigDecimal totalPaid;
        private BigDecimal totalSale;
        private Invoice invoice;
        private Debt debt;
        
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder numSale(Integer numSale) {
            this.numSale = numSale;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder totalPaid(BigDecimal totalPaid) {
            this.totalPaid = totalPaid;
            return this;
        }

        public Builder totalSale(BigDecimal totalSale) {
            this.totalSale = totalSale;
            return this;
        }

        public Builder invoice(Invoice invoice) {
            this.invoice = invoice;
            return this;
        }

        public Builder debt(Debt debt) {
            this.debt = debt;
            return this;
        }
        
        public Sale build() {
            return new Sale(this);
        }

    }

}
