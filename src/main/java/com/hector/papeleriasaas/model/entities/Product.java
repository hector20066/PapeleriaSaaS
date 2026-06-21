package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Product {

    private Integer id;
    private String description;
    private Integer amount;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private final List<Provider> providers;

    public Product() {
        this.providers = new ArrayList<>();
    }

    public Product(Builder builder) {
        this();
        this.id = builder.id;
        this.description = builder.description;
        this.amount = builder.amount;
        this.salePrice = builder.salePrice;
        this.purchasePrice = builder.purchasePrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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

    public List<Provider> getProviders() {
        return providers;
    }

    public Product addProvider(Provider provider) {
        this.providers.add(provider);
        return this;
    }

    public static class Builder {

        private Integer id;
        private String description;
        private Integer amount;
        private BigDecimal salePrice;
        private BigDecimal purchasePrice;

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

        public Product build() {
            return new Product(this);
        }

    }

}
