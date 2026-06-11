package com.hector.papeleriasaas.model.dto.product;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductDetails {

    private int id;
    private String description;
    private int amount;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private String supplier;
    private BigDecimal earnings;

    public DTOProductDetails() {

    }

    public DTOProductDetails(Builder builder) {
        id = builder.id;
        description = builder.description;
        amount = builder.amount;
        purchasePrice = builder.purchasePrice;
        salePrice = builder.salePrice;
        supplier = builder.supplier;
        earnings = builder.earnings;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        DTOProductDetails that = (DTOProductDetails) object;

        return id == that.id && Objects.equals(description, that.description)
                && Objects.equals(purchasePrice, that.purchasePrice)
                && Objects.equals(salePrice, that.salePrice)
                && Objects.equals(supplier, that.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, purchasePrice, salePrice, supplier);
    }

    public static class Builder {

        private int id;
        private String description;
        private int amount;
        private BigDecimal purchasePrice;
        private BigDecimal salePrice;
        private String supplier;
        private BigDecimal earnings;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
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

        public Builder supplier(String supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder earnings(BigDecimal earnings) {
            this.earnings = earnings;
            return this;
        }

        public DTOProductDetails build() {
            return new DTOProductDetails(this);
        }

    }

}
