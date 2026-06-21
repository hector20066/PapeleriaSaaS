package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetail {

    private Integer id;
    private Product product;
    private Integer amount;
    private BigDecimal unitPrice;

    public PurchaseDetail() {

    }

    public PurchaseDetail(Integer id, Product product, Integer amount, BigDecimal unitPrice) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
