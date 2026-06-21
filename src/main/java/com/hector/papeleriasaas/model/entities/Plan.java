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

    public Plan(Builder builder) {

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

    public static class Builder {

        private Integer id;
        private String name;
        private BigDecimal amount;
        private String description;
        private BillingCycle billingCycle;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder billingCycle(BillingCycle billingCycle) {
            this.billingCycle = billingCycle;
            return this;
        }

        public Plan build() {
            return new Plan(this);
        }

    }

}
