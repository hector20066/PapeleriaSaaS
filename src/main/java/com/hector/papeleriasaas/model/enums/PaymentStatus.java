package com.hector.papeleriasaas.model.enums;

/**
 *
 * @author Ing. Hector Acevedo
 */
public enum PaymentStatus {

    PENDING("Pendiente", "Sin pagar."),
    PAID("Pagado", "Pago completo");

    private final String value;
    private final String description;

    PaymentStatus(String value, String description) {
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
