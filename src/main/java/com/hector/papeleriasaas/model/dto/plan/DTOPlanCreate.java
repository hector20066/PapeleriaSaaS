package com.hector.papeleriasaas.model.dto.plan;

import java.math.BigDecimal;

import com.hector.papeleriasaas.model.enums.BillingCycle;

public class DTOPlanCreate {

    private String name;
    private BigDecimal price;
    private String description;
    private BillingCycle billingCycle;

    public DTOPlanCreate() {

    }

    public DTOPlanCreate(String name, BigDecimal price, String description, BillingCycle billingCycle) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.billingCycle = billingCycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
