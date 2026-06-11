package com.hector.papeleriasaas.model.dto;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleDetailsCreate {

    private int numSale;
    private int productId;
    private int amount;
    private BigDecimal unitPrice;

    public DTOSaleDetailsCreate() {

    }

    public DTOSaleDetailsCreate(int numSale, int productId, int amount, BigDecimal unitPrice) {
        this.numSale = numSale;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public int getNumSale() {
        return numSale;
    }

    public void setNumSale(int numSale) {
        this.numSale = numSale;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
