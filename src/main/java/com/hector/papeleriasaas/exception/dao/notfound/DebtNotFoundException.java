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
        super("No se han encontrado deudas para el parámetro de búsqueda: '" + param + "'");
    }

}
