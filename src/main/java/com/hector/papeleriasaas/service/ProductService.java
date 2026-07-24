package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.exception.dao.DAOException;
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
public class ProductService {

    private final DAOProducts daoProducts;
    private int companyId = 0; // se debe obtener

    public ProductService(DAOProducts daoProducts) {
        this.daoProducts = daoProducts;
    }

    public List<DTOProductDetails> getProductList() {
        return daoProducts.getProductList();
    }

    public int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws DAOException {
        DTOProductCreate dto = new DTOProductCreate();
        dto.setDescription(description);
        dto.setPurchasePrice(purchasePrice);
        dto.setSalePrice(salePrice);
        dto.setCompanyId(companyId);

        return daoProducts.add(connection, dto);
    }

    public Optional<DTOProductDetails> findByName(String product) throws DAOException {
        return daoProducts.findByName(product, companyId);
    }

    public Optional<DTOProductDetails> findByName(Connection connection, String product) throws DAOException {
        return daoProducts.findByName(connection, product, companyId);
    }

    public Optional<DTOProductDetails> findById(int id) throws DAOException {
        return daoProducts.findById(id, companyId);
    }

    public Optional<DTOProductDetails> findById(Connection connection, int id) throws DAOException {
        return daoProducts.findById(connection, id, companyId);
    }

    public Optional<DTOProductBestSelling> bestSelling() throws DAOException {
        return daoProducts.bestSelling(companyId);
    }

    public List<DTOProductDetails> findByAll() throws DAOException {
        return daoProducts.findByAll(companyId);
    }

    public void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException {
        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .companyId(companyId)
                .build();

        daoProducts.modify(dto);
    }

    public void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice,
                       int id) throws DAOException {
        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .companyId(companyId)
                .build();

        daoProducts.modify(connection, dto);
    }

    public void delete(int id) throws DAOException {
        daoProducts.delete(id, companyId);
    }

    public int countRecords() throws DAOException {
        return daoProducts.countRecords(companyId);
    }

}
