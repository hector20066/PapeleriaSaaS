package com.hector.papeleriasaas.model.dto.plan;

import java.math.BigDecimal;

import com.hector.papeleriasaas.model.enums.BillingCycle;

public class DTOPlanCreate {

    private String name;
    private BigDecimal amount;
    private String description;
    private BillingCycle billingCycle;

    public DTOPlanCreate() {

    }

    public DTOPlanCreate(String name, BigDecimal amount, String description, BillingCycle billingCycle) {
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.billingCycle = billingCycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BillingCycle getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(BillingCycle billingCycle) {
        this.billingCycle = billingCycle;
    }

}
