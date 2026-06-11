package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleDetail {

    private Product product;
    private int quantity;
    private BigDecimal unitPrice;

    public SaleDetail() {

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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
