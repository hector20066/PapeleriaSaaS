package com.hector.papeleriasaas.model.dto.purchase;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseCreate {

    private Integer providerId;
    private BigDecimal totalPaid;
    private Integer companyId;

    public DTOPurchaseCreate() {

    }

    public DTOPurchaseCreate(Integer providerId, BigDecimal totalPaid, Integer companyId) {
        this.providerId = providerId;
        this.totalPaid = totalPaid;
        this.companyId = companyId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
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
