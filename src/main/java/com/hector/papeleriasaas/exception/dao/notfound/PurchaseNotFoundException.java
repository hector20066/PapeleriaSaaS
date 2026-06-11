package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseNotFoundException extends DataNotFoundException {

    public PurchaseNotFoundException() {
        super("Compra no encontrada");
    }

    public PurchaseNotFoundException(String param) {
        super("Compra no encontrada con parámetro: '" + param + "'");
    }

}
