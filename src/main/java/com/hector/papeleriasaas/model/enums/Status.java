package com.hector.papeleriasaas.model.enums;

public enum Status {

    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
