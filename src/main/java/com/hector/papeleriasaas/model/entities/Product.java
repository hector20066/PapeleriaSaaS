package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Product {

    private int id;
    private String description;
    private int amount;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private final List<Provider> providers;

    public Product() {
        this.providers = new ArrayList<>();
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

    public List<Provider> getProviders() {
        return providers;
    }

    public Product addProvider(Provider provider) {
        this.providers.add(provider);
        return this;
    }

}
