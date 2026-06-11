package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
@FunctionalInterface
public interface PurchaseDetailsFacade {

    void add(Connection connection, int purchaseNum, int productId, int amount, BigDecimal unitPrice) throws DAOException;

}
