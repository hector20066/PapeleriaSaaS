package com.hector.papeleriasaas.facade.impl;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.model.dao.impl.DAOProductsImpl;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProducts;
import com.hector.papeleriasaas.model.dto.product.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProductImplFacade implements ProductsFacade {

    private final DAOProducts daoProducts;

    public ProductImplFacade() {
        this.daoProducts = new DAOProductsImpl();
    }

    @Override
    public List<DTOProductDetails> getProductList() {
        return daoProducts.getProductList();
    }

    @Override
    public int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws DAOException {
        DTOProductCreate dto = new DTOProductCreate();
        dto.setDescription(description);
        dto.setPurchasePrice(purchasePrice);
        dto.setSalePrice(salePrice);

        return daoProducts.add(connection, dto);
    }

    @Override
    public Optional<DTOProductDetails> findByName(String product) throws DAOException {
        return daoProducts.findByName(product);
    }

    @Override
    public Optional<DTOProductDetails> findByName(Connection connection, String product) throws DAOException {
        return daoProducts.findByName(connection, product);
    }

    @Override
    public Optional<DTOProductDetails> findById(int id) throws DAOException {
        return daoProducts.findById(id);
    }

    @Override
    public Optional<DTOProductDetails> findById(Connection connection, int id) throws DAOException {
        return daoProducts.findById(connection, id);
    }

    @Override
    public Optional<DTOProductBestSelling> bestSelling() throws DAOException {
        return daoProducts.bestSelling();
    }

    @Override
    public List<DTOProductDetails> findByAll() throws DAOException {
        return daoProducts.findByAll();
    }

    @Override
    public void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException {
        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .build();

        daoProducts.modify(dto);
    }

    @Override
    public void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice,
                       int id) throws DAOException {
        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .build();

        daoProducts.modify(connection, dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoProducts.delete(id);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoProducts.countRecords();
    }

}
