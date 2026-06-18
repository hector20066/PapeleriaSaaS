package com.hector.papeleriasaas.model.enums;

public enum BillingCycle {

    MONTHLY("Mensual"),
    YEARLY("Anual");

    private final String value;

    BillingCycle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
