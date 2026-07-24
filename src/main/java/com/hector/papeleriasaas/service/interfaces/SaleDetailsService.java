package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;

import java.math.BigDecimal;
import java.sql.Connection;

public interface SaleDetailsService {

    void add(Connection connection, int numSale, int productId, int amount, BigDecimal unitPrice) throws DAOException;

}
