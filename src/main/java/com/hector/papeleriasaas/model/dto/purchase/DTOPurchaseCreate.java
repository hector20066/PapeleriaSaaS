package com.hector.papeleriasaas.model.dto.purchase;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOPurchaseCreate {

    private Integer providerId;
    private BigDecimal totalPurchase;
    private Integer companyId;

    public DTOPurchaseCreate() {

    }

    public DTOPurchaseCreate(Integer providerId, BigDecimal totalPurchase, Integer companyId) {
        this.providerId = providerId;
        this.totalPurchase = totalPurchase;
        this.companyId = companyId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public BigDecimal getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(BigDecimal totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
