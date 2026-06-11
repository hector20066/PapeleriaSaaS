package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleNotFoundException extends DataNotFoundException {

    public SaleNotFoundException() {
        super("Venta no encontrada");
    }

    public SaleNotFoundException(String param) {
        super("Venta no encontrada con parámetro: '" + param + "'");
    }

}
