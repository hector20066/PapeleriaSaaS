package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Purchase {

    private Integer id;
    private Integer purchaseNumber;
    private LocalDate date;
    private BigDecimal total;
    private Provider provider;
    private final List<PurchaseDetail> details;

    public Purchase() {
        this.details = new ArrayList<>();
    }

    public Purchase(Builder builder) {
        this();
        this.id = builder.id;
        this.purchaseNumber = builder.purchaseNumber;
        this.date = builder.date;
        this.total = builder.total;
        this.provider = builder.provider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<PurchaseDetail> getDetails() {
        return details;
    }

    public Purchase addPurchaseDetails(PurchaseDetail detail) {
        this.details.add(detail);
        return this;
    }

    public static class Builder {

        private Integer id;
        private Integer purchaseNumber;
        private LocalDate date;
        private BigDecimal total;
        private Provider provider;
        
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder purchaseNumber(Integer purchaseNumber) {
            this.purchaseNumber = purchaseNumber;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Builder provider(Provider provider) {
            this.provider = provider;
            return this;
        }

        public Purchase build() {
            return new Purchase(this);
        }

    }

}
