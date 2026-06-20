package com.hector.papeleriasaas.model.dto;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleDetailsCreate {

    private Integer numSale;
    private Integer productId;
    private Integer amount;
    private BigDecimal unitPrice;

    public DTOSaleDetailsCreate() {

    }

    public DTOSaleDetailsCreate(Integer numSale, Integer productId, Integer amount, BigDecimal unitPrice) {
        this.numSale = numSale;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public Integer getNumSale() {
        return numSale;
    }

    public void setNumSale(Integer numSale) {
        this.numSale = numSale;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
