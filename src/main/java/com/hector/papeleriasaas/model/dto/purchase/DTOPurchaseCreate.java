package com.hector.papeleriasaas.model.dto.purchase;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseCreate {

    private int supplierNit;
    private BigDecimal totalPaid;

    public DTOPurchaseCreate() {

    }

    public DTOPurchaseCreate(int supplierNit, BigDecimal totalPaid) {
        this.supplierNit = supplierNit;
        this.totalPaid = totalPaid;
    }

    public int getSupplierNit() {
        return supplierNit;
    }

    public void setSupplierNit(int supplierNit) {
        this.supplierNit = supplierNit;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

}
