package com.hector.papeleriasaas.model.dto.plan;

import com.hector.papeleriasaas.model.enums.BillingCycle;

import java.math.BigDecimal;

public class DTOPlanDetails {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private BillingCycle billingCycle;

    public DTOPlanDetails() {

    }

    public DTOPlanDetails(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.billingCycle = builder.billingCycle;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public BillingCycle getBillingCycle() {
        return billingCycle;
    }

    public static class Builder {

        private Integer id;
        private String name;
        private BigDecimal price;
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

        public Builder price(BigDecimal price) {
            this.price = price;
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

        public DTOPlanDetails build() {
            return new DTOPlanDetails(this);
        }

    }

}
