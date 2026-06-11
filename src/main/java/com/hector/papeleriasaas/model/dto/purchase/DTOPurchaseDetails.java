package com.hector.papeleriasaas.model.dto.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseDetails {

    private int numPurchase;
    private String productDescription;
    private int productId;
    private int quantityPurchased;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private LocalDate date;
    private String supplier;
    private int supplierNit;
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
        this.supplier = builder.supplier;
        this.supplierNit = builder.supplierNit;
        this.totalPaid = builder.totalPaid;
        this.earnings = builder.earnings;
    }

    public int getNumPurchase() {
        return numPurchase;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantityPurchased() {
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

    public String getSupplier() {
        return supplier;
    }

    public int getSupplierNit() {
        return supplierNit;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public static class Builder {

        private int numPurchase;
        private String productDescription;
        private int productId;
        private int quantityPurchased;
        private BigDecimal purchasePrice;
        private BigDecimal salePrice;
        private LocalDate date;
        private String supplier;
        private int supplierNit;
        private BigDecimal totalPaid;
        private BigDecimal earnings;

        public Builder numPurchase(int numSale) {
            this.numPurchase = numSale;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantityPurchased(int quantityPurchased) {
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

        public Builder supplier(String supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder supplierNit(int supplierNit) {
            this.supplierNit = supplierNit;
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
