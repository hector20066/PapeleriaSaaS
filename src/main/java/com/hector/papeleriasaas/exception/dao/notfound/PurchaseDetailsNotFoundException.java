package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseDetailsNotFoundException extends DataNotFoundException {

    public PurchaseDetailsNotFoundException() {
        super("Detalles de compra no encontrados");
    }

    public PurchaseDetailsNotFoundException(String param) {
        super("Detalles de compra no encontrados con parámetro: '" + param + "'");
    }

}
