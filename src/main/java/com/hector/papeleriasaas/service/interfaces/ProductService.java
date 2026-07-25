package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface ProductService {

    List<DTOProductDetails> getProductList();
    int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws DAOException;
    DTOProductDetails findByName(String product) throws DAOException;
    DTOProductDetails findByName(Connection connection, String product) throws DAOException;
    DTOProductDetails findById(int id) throws DAOException;
    DTOProductDetails findById(Connection connection, int id) throws DAOException;
    DTOProductBestSelling bestSelling() throws DAOException;
    List<DTOProductDetails> findByAll() throws DAOException;
    void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException;
    void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException;
    void delete(int id) throws DAOException;
    int countRecords() throws DAOException;

}
