package com.hector.papeleriasaas.model.dto.product;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductCreate {

    private String description;
    private Integer amount;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Integer companyId;

    public DTOProductCreate() {

    }

    public DTOProductCreate(Builder builder) {
        this.description = builder.description;
        this.amount = builder.amount;
        this.purchasePrice = builder.purchasePrice;
        this.salePrice = builder.salePrice;
        this.companyId = builder.companyId;
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

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public static class Builder {

        private String description;
        private Integer amount;
        private BigDecimal purchasePrice;
        private BigDecimal salePrice;
        private Integer companyId;

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(Integer amount) {
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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public DTOProductCreate build() {
            return new DTOProductCreate(this);
        }

    }

}
