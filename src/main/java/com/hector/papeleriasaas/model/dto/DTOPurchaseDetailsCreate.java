package com.hector.papeleriasaas.model.dto;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseDetailsCreate {

    private Integer purchaseNum;
    private Integer productId;
    private Integer amount;
    private BigDecimal unitPrice;

    public DTOPurchaseDetailsCreate() {

    }

    public DTOPurchaseDetailsCreate(Integer purchaseNum, Integer productId, Integer amount, BigDecimal unitPrice) {
        this.purchaseNum = purchaseNum;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
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
