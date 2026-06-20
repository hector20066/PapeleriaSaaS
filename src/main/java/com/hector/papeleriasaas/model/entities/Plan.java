package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;

import com.hector.papeleriasaas.model.enums.BillingCycle;

public class Plan {

    private Integer id;
    private String name;
    private BigDecimal amount;
    private String description;
    private BillingCycle billingCycle;

    public Plan() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
