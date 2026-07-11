package com.hector.papeleriasaas.model.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseDetails {

    private Integer numPurchase;
    private String productDescription;
    private Integer productId;
    private Integer quantityPurchased;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private LocalDate date;
    private String provider;
    private Integer providerNit;
    private BigDecimal totalPaid;
    private BigDecimal earnings;

    public DTOPurchaseDetails() {

    }

    public DTOPurchaseDetails(Builder builder) {
        this.numPurchase = builder.numPurchase;
        this.productDescription = builder.productDescription;
        this.productId = builder.productId;
        this.quantityPurchased = builder.quantityPurchased;
        this.purchasePrice = builder.purchasePrice;
        this.salePrice = builder.salePrice;
        this.date = builder.date;
        this.provider = builder.provider;
        this.providerNit = builder.providerNit;
        this.totalPaid = builder.totalPaid;
        this.earnings = builder.earnings;
    }

    public Integer getNumPurchase() {
        return numPurchase;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantityPurchased() {
        return quantityPurchased;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProvider() {
        return provider;
    }

    public Integer getProviderNit() {
        return providerNit;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public static class Builder {

        private Integer numPurchase;
        private String productDescription;
        private Integer productId;
        private Integer quantityPurchased;
        private BigDecimal purchasePrice;
        private BigDecimal salePrice;
        private LocalDate date;
        private String provider;
        private Integer providerNit;
        private BigDecimal totalPaid;
        private BigDecimal earnings;

        public Builder numPurchase(Integer numSale) {
            this.numPurchase = numSale;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantityPurchased(Integer quantityPurchased) {
            this.quantityPurchased = quantityPurchased;
            return this;
        }

        public Builder purchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        public Builder salePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder providerNit(Integer providerNit) {
            this.providerNit = providerNit;
            return this;
        }

        public Builder totalPaid(BigDecimal totalPaid) {
            this.totalPaid = totalPaid;
            return this;
        }

        public Builder earnings(BigDecimal earnings) {
            this.earnings = earnings;
            return this;
        }

        public DTOPurchaseDetails build() {
            return new DTOPurchaseDetails(this);
        }

    }

}
