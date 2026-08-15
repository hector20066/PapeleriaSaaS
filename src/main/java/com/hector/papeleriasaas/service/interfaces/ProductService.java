package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProductService {

    List<DTOProductDetails> getProductList();
    int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws ServiceException;
    DTOProductDetails findByName(String product) throws ServiceException;
    DTOProductDetails findByName(Connection connection, String product) throws ServiceException;
    DTOProductDetails findById(int id) throws ServiceException;
    DTOProductDetails findById(Connection connection, int id) throws ServiceException;
    DTOProductBestSelling bestSelling() throws ServiceException;
    List<DTOProductDetails> findByAll() throws ServiceException;
    void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws ServiceException;
    void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws ServiceException;
    void delete(int id) throws ServiceException;
    int countRecords() throws ServiceException;

}
