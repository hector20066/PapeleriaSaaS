package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetail {

    private int id;
    private Product product;
    private int amount;
    private BigDecimal unitPrice;

    public PurchaseDetail() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
