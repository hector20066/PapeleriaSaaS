package com.hector.papeleriasaas.model.dto.sale;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleUpdate {

    private int numSale;
    private BigDecimal totalPaid;

    public DTOSaleUpdate() {

    }

    public DTOSaleUpdate(int numSale, BigDecimal totalPaid) {
        this.numSale = numSale;
        this.totalPaid = totalPaid;
    }

    public int getNumSale() {
        return numSale;
    }

    public void setNumSale(int numSale) {
        this.numSale = numSale;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

}
