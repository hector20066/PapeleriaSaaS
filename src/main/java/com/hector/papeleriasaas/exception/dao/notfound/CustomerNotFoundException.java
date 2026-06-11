package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CustomerNotFoundException extends DataNotFoundException {

    public CustomerNotFoundException() {
        super("Cliente no encontrado");
    }

    public CustomerNotFoundException(String param) {
        super("Cliente no encontrado con parámetro: '" + param + "'");
    }

}
