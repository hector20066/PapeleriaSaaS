package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.PurchaseDetailsNotFoundException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.service.interfaces.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseServiceImpl implements PurchaseService {

    private final DAOPurchases daoPurchases;
    private final ProvidersService providersService;
    private final ProductService productService;
    private final PurchaseDetailsService purchaseDetailsService;
    private final ProviderProductsService providerProductsService;
    private final ConnectionProvider connectionProvider;

    private final Session session;

    public PurchaseServiceImpl(DAOPurchases daoPurchases, ProvidersService providersService, ProductService productService,
                               PurchaseDetailsService purchaseDetailsService, ProviderProductsService providerProductsService,
                               ConnectionProvider connectionProvider, Session session) {
        this.daoPurchases = daoPurchases;
        this.providersService = providersService;
        this.productService = productService;
        this.purchaseDetailsService = purchaseDetailsService;
        this.providerProductsService = providerProductsService;
        this.connectionProvider = connectionProvider;
        this.session = session;
    }

    @Override
    public void registerPurchase(DTOProvider dtoSupplier, BigDecimal totalPaid, List<DTOProductCreate> products)
            throws ServiceException {
        Connection connection = null;

        try {
            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            int providerId = providersService.registerProviderPurchase(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(),
                    dtoSupplier.getAddress(), dtoSupplier.getPhone(), dtoSupplier.getEmail());

            int purchaseId = this.add(connection, providerId, totalPaid);

            for (DTOProductCreate dtoProduct : products) {
                int productId;
                DTOProductDetails dtoProductFound = productService.findByName(connection, dtoProduct.getDescription());

                if (dtoProductFound == null) {
                    productId = productService.add(connection, dtoProduct.getDescription(), dtoProduct.getPurchasePrice(),
                            dtoProduct.getSalePrice());

                    providerProductsService.add(connection, providerId, productId);
                } else {
                    productService.modify(connection, dtoProduct.getDescription(), dtoProductFound.getAmount(),
                            dtoProduct.getSalePrice(), dtoProduct.getPurchasePrice(), dtoProductFound.getId());

                    productId = dtoProductFound.getId();
                }

                purchaseDetailsService.add(connection, purchaseId, productId, dtoProduct.getAmount(),
                        dtoProduct.getPurchasePrice());
            }

            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                rollback(connection);
            }

            throw new ServicePersistenceException("No fue posible registrar la compra.", e);
        } catch (ServiceException e) {
            rollback(connection);
            throw e;
        } finally {
            if (connection != null) {
                close(connection);
            }
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ignored) {
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }

    private int add(Connection connection, int providerId, BigDecimal totalPaid) throws DAOException {
        DTOPurchaseCreate dto = new DTOPurchaseCreate();
        dto.setProviderId(providerId);
        dto.setTotalPurchase(totalPaid);
        dto.setCompanyId(session.getCompanyId());

        return daoPurchases.add(connection, dto);
    }

    @Override
    public List<DTOPurchaseDetails> findById(int id) throws ServiceException {
        try {
            List<DTOPurchaseDetails> list = daoPurchases.findById(id, session.getCompanyId());

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return list;
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findById(Connection connection, int id) throws ServiceException {
        try {
            List<DTOPurchaseDetails> list = daoPurchases.findById(connection, id, session.getCompanyId());

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException(String.valueOf(id));
            }

            return list;
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOPurchaseDetails> findByAll() throws ServiceException {
        try {
            return daoPurchases.findByAll(session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoPurchases.delete(id, session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int countRecords() throws ServiceException {
        try {
            return daoPurchases.countRecords(session.getCompanyId());
        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
