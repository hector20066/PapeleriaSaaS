package com.hector.papeleriasaas.facade.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.IRemovable;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProductsFacade extends IListableFacade<DTOProductDetails>,
        IRemovableFacade,
        ICountableFacade {

    int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws DAOException;
    Optional<DTOProductDetails> findByName(String product) throws DAOException;
    Optional<DTOProductDetails> findByName(Connection connection, String product) throws DAOException;
    Optional<DTOProductBestSelling> bestSelling() throws DAOException;
    List<DTOProductDetails> getProductList();
    void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException;
    void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice,
                int id) throws DAOException;

}
