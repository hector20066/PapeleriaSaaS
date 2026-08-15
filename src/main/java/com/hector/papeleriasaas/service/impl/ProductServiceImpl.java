package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
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

    @Override
    public List<DTOProductDetails> getProductList() {
        return daoProducts.getProductList();
    }

    @Override
    public int add(Connection connection, String description, BigDecimal purchasePrice, BigDecimal salePrice) throws ServiceException {
        try {
            DTOProductCreate dto = new DTOProductCreate();
            dto.setDescription(description);
            dto.setPurchasePrice(purchasePrice);
            dto.setSalePrice(salePrice);
            dto.setCompanyId(session.getCompanyId());

            return daoProducts.add(connection, dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProductDetails findByName(String product) throws ServiceException {
        try {
            Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findByName(product, session.getCompanyId());

            if (dtoProductDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(product);
            }

            return dtoProductDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProductDetails findByName(Connection connection, String product) throws ServiceException {
        try {
            Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findByName(connection, product, session.getCompanyId());

            return dtoProductDetailsOptional.orElse(null);


        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProductDetails findById(int id) throws ServiceException {
        try {
            Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findById(id, session.getCompanyId());

            if (dtoProductDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return dtoProductDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProductDetails findById(Connection connection, int id) throws ServiceException {
        try {
            Optional<DTOProductDetails> dtoProductDetailsOptional = daoProducts.findById(connection, id, session.getCompanyId());

            return dtoProductDetailsOptional.orElse(null);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOProductBestSelling bestSelling() throws ServiceException {
        try {
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

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOProductDetails> findByAll() throws ServiceException {
        try {
            return daoProducts.findByAll(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice, int id) throws ServiceException {
        try {
            if (amount < 0) {
                throw new ServicePersistenceException("La cantidad disponible no puede ser negativa.");
            }

            if (purchasePrice.signum() <= 0 || salePrice.signum() < 0) {
                throw new ServicePersistenceException("Los precios no pueden ser negativos o ceros");
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

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(Connection connection, String description, int amount, BigDecimal salePrice, BigDecimal purchasePrice,
                       int id) throws ServiceException {
        try {
            if (amount < 0) {
                throw new ServicePersistenceException("La cantidad disponible no puede ser negativa.");
            }

            if (purchasePrice.signum() <= 0 || salePrice.signum() < 0) {
                throw new ServicePersistenceException("Los precios no pueden ser negativos o ceros");
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

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoProducts.delete(id, session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int countRecords() throws ServiceException {
        try {
            return daoProducts.countRecords(session.getCompanyId());

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
