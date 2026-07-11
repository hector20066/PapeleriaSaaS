package com.hector.papeleriasaas.model.dto.sale;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleUpdate {

    private Integer numSale;
    private BigDecimal totalPaid;
    private Integer companyId;

    public DTOSaleUpdate() {

    }

    public DTOSaleUpdate(int numSale, BigDecimal totalPaid, Integer companyId) {
        this.numSale = numSale;
        this.totalPaid = totalPaid;
        this.companyId = companyId;
    }

    public Integer getNumSale() {
        return numSale;
    }

    public void setNumSale(Integer numSale) {
        this.numSale = numSale;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
