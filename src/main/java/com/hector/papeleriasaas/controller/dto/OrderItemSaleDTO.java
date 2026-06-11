package com.hector.papeleriasaas.controller.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class OrderItemSaleDTO {

    private String productName;
    private int productId;
    private int amount;
    private BigDecimal unitPrice;

    public OrderItemSaleDTO() {

    }

    public OrderItemSaleDTO(String productName, int productId, int amount, BigDecimal unitPrice) {
        this.productName = productName;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        OrderItemSaleDTO that = (OrderItemSaleDTO) object;
        return productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

}
