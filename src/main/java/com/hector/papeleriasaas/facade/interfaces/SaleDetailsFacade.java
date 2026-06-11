package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface SaleDetailsFacade {

    void add(Connection connection, int numSale, int productId, int amount, BigDecimal unitPrice) throws DAOException;

}
