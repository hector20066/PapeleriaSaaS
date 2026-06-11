package com.hector.papeleriasaas.exception.dao.notfound;

import com.hector.papeleriasaas.exception.dao.DataNotFoundException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DebtNotFoundException extends DataNotFoundException {

    public DebtNotFoundException() {
        super("Deuda no encontrada");
    }

    public DebtNotFoundException(String param) {
        super("Deuda no encontrada con parámetro: '" + param + "'");
    }

}
