package com.hector.papeleriasaas.model.dto.sale;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleCreate {

    private Integer customerId;
    private BigDecimal totalPaid;
    private BigDecimal totalSale;

    public DTOSaleCreate() {

    }

    public DTOSaleCreate(Integer customerId, BigDecimal totalPaid, BigDecimal totalSale) {
        this.customerId = customerId;
        this.totalPaid = totalPaid;
        this.totalSale = totalSale;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }

}
