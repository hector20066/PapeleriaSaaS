package com.hector.papeleriasaas.model.dto.product;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductUpdate {

    private int id;
    private String description;
    private int amount;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;

    public DTOProductUpdate() {

    }

    public DTOProductUpdate(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.amount = builder.amount;
        this.salePrice = builder.salePrice;
        this.purchasePrice = builder.purchasePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public static class Builder {

        private int id;
        private String description;
        private int amount;
        private BigDecimal salePrice;
        private BigDecimal purchasePrice;

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

        public Builder salePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder purchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
            return this;
        }

        public DTOProductUpdate build() {
            return new DTOProductUpdate(this);
        }

    }

}
