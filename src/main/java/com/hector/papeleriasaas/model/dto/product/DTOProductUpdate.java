package com.hector.papeleriasaas.model.dto.product;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductUpdate {

    private Integer id;
    private String description;
    private Integer amount;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private Integer companyId;

    public DTOProductUpdate() {

    }

    public DTOProductUpdate(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.amount = builder.amount;
        this.salePrice = builder.salePrice;
        this.purchasePrice = builder.purchasePrice;
        this.companyId = builder.companyId;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public static class Builder {

        private Integer id;
        private String description;
        private Integer amount;
        private BigDecimal salePrice;
        private BigDecimal purchasePrice;
        private Integer companyId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder amount(Integer amount) {
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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public DTOProductUpdate build() {
            return new DTOProductUpdate(this);
        }

    }

}
