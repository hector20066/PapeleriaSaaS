package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetail {

    private Product product;
    private int quantity;
    private BigDecimal unitCost;

    public PurchaseDetail() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

}
