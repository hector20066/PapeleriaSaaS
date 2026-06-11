package com.hector.papeleriasaas.model.dto.product;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductCreate {

    private String description;
    private int amount;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;

    public DTOProductCreate() {

    }

    public DTOProductCreate(String description, int amount, BigDecimal purchasePrice, BigDecimal salePrice) {
        this.description = description;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
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

}
