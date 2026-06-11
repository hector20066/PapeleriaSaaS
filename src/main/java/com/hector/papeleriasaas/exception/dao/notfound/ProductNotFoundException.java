package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProductNotFoundException extends DataNotFoundException {

    public ProductNotFoundException() {
        super("Producto no encontrado");
    }

    public ProductNotFoundException(String param) {
        super("Producto no encontrado con parámetro: '" + param + "'");
    }

}
