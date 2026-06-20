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
    private Integer companyId;

    public DTOSaleCreate() {

    }

    public DTOSaleCreate(Integer customerId, BigDecimal totalPaid, BigDecimal totalSale, Integer companyId) {
        this.customerId = customerId;
        this.totalPaid = totalPaid;
        this.totalSale = totalSale;
        this.companyId = companyId;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
