package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleDetailsNotFoundException extends DataNotFoundException {

    public SaleDetailsNotFoundException() {
        super("Detalles de venta no encontrados");
    }

    public SaleDetailsNotFoundException(String param) {
        super("Detalles de venta no encontrados con parámetro: '" + param + "'");
    }

}
