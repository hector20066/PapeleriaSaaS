package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface SaleDetailsService {

    void add(Connection connection, int numSale, int productId, int amount, BigDecimal unitPrice) throws ServiceException;

}
