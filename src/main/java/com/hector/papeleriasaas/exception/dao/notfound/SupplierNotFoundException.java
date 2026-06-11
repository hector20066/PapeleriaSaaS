package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SupplierNotFoundException extends DataNotFoundException {

    public SupplierNotFoundException() {
        super("Proveedor no encontrado");
    }

    public SupplierNotFoundException(String param) {
        super("Proveedor no encontrado con parámetro: '" + param + "'");
    }

}
