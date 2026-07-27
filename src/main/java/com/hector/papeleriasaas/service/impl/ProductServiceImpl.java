package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.dao.persistence.ProductPersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOProducts;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.product.DTOProductUpdate;
import com.hector.papeleriasaas.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ProductServiceImpl implements ProductService {

    private final DAOProducts daoProducts;
    private final Session session;

    public ProductServiceImpl(DAOProducts daoProducts, Session session) {
        this.daoProducts = daoProducts;
        this.session = session;
    }

    public List<DTOProductDetails> getProductList() {
        return daoProducts.getProductList();
    }

    @Override
    public int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws DAOException {
        DTOProductCreate dto = new DTOProductCreate();
        dto.setDescription(description);
        dto.setPurchasePrice(purchasePrice);
        dto.setSalePrice(salePrice);
        dto.setCompanyId(session.getCompanyId());

        return daoProducts.add(connection, dto);
    }

    @Override
    public DTOProductDetails findByName(String product) throws DAOException {
        Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findByName(product, session.getCompanyId());

        if (dtoProductDetailsOptional.isEmpty()) {
            throw new ProductNotFoundException(product);
        }

        return dtoProductDetailsOptional.get();
    }

    @Override
    public DTOProductDetails findByName(Connection connection, String product) throws DAOException {
        Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findByName(connection, product, session.getCompanyId());

        return dtoProductDetailsOptional.orElse(null);

    }

    @Override
    public DTOProductDetails findById(int id) throws DAOException {
        Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findById(id, session.getCompanyId());

        if (dtoProductDetailsOptional.isEmpty()) {
            throw new ProductNotFoundException(String.valueOf(id));
        }

        return dtoProductDetailsOptional.get();
    }

    @Override
    public DTOProductDetails findById(Connection connection, int id) throws DAOException {
        Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findById(connection, id, session.getCompanyId());

        return dtoProductDetailsOptional.orElse(null);
    }

    @Override
    public DTOProductBestSelling bestSelling() throws DAOException {
        Optional<DTOProductBestSelling> productBestSellingOptional = daoProducts.bestSelling(session.getCompanyId());

        DTOProductBestSelling bestSelling = new DTOProductBestSelling.Builder()
                .id(0)
                .description("")
                .quantitySold(0)
                .build();

        if (productBestSellingOptional.isPresent()) {
            bestSelling = productBestSellingOptional.get();
        }

        return bestSelling;
    }

    @Override
    public List<DTOProductDetails> findByAll() throws DAOException {
        return daoProducts.findByAll(session.getCompanyId());
    }

    @Override
    public void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws DAOException {
        if (amount < 0) {
            throw new ProductPersistenceException("La cantidad disponible no puede ser negativa.");
        }

        if (purchasePrice.signum() <= 0 || salePrice.signum() < 0) {
            throw new ProductPersistenceException("Los precios no pueden ser negativos o ceros");
        }

        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .companyId(session.getCompanyId())
                .build();

        daoProducts.modify(dto);
    }

    @Override
    public void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice,
                       int id) throws DAOException {
        if (amount < 0) {
            throw new ProductPersistenceException("La cantidad disponible no puede ser negativa.");
        }

        if (purchasePrice.signum() <= 0 || salePrice.signum() < 0) {
            throw new ProductPersistenceException("Los precios no pueden ser negativos o ceros");
        }

        DTOProductUpdate dto = new DTOProductUpdate.Builder()
                .description(description)
                .amount(amount)
                .salePrice(salePrice)
                .purchasePrice(purchasePrice)
                .id(id)
                .companyId(session.getCompanyId())
                .build();

        daoProducts.modify(connection, dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoProducts.delete(id, session.getCompanyId());
    }

    @Override
    public int countRecords() throws DAOException {
        return daoProducts.countRecords(session.getCompanyId());
    }

}
