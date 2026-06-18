package com.hector.papeleriasaas.model.enums;

/**
 *
 * @author Ing. Hector Acevedo
 */
public enum StatusDebt {

    PENDING("Pendiente", "Deuda sin pagar."),
    PAID("Pagado", "Pago de la deuda completo");

    private final String value;
    private final String description;

    StatusDebt(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

}
