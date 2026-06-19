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

}
