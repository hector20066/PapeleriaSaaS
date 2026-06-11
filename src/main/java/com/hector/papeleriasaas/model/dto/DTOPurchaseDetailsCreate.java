package com.hector.papeleriasaas.model.dto;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseDetailsCreate {

    private int purchaseNum;
    private int productId;
    private int amount;
    private BigDecimal unitPrice;

    public DTOPurchaseDetailsCreate() {

    }

    public DTOPurchaseDetailsCreate(int purchaseNum, int productId, int amount, BigDecimal unitPrice) {
        this.purchaseNum = purchaseNum;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
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
