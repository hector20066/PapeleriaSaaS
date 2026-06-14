package com.hector.papeleriasaas.model.enums;

public enum Roles {

    ROOT("Root"),
    ADMIN("Administrador"),
    EMPLOYEE("Empleado"),
    OTHER("Otro");

    private final String value;

    Roles(String value) {
      this.value = value;
    }

    public String getValue() {
        return value;
    }

}

