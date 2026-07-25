package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.ProvidersService;
import com.hector.papeleriasaas.service.interfaces.PurchaseDetailsService;
import com.hector.papeleriasaas.service.interfaces.PurchaseService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseServiceImpl implements PurchaseService {

    private final DAOPurchases daoPurchases;
    private final ProvidersService providersService;
    private final ProductService productService;
    private final PurchaseDetailsService purchaseDetailsService;
    private final ConnectionProvider connectionProvider;

    private int companyId = 0;

    public PurchaseServiceImpl(DAOPurchases daoPurchases, ProvidersService providersService, ProductService productService, PurchaseDetailsService purchaseDetailsService, ConnectionProvider connectionProvider) {
        this.daoPurchases = daoPurchases;
        this.providersService = providersService;
        this.productService = productService;
        this.purchaseDetailsService = purchaseDetailsService;
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void registerPurchase(DTOProvider dtoSupplier, BigDecimal totalPaid, List<DTOProductCreate> products)
            throws SQLException, DAOException {
        Connection connection = null;

        try {
            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            if (providersService.findById(connection, dtoSupplier.getNit()).isEmpty()) {
                providersService.add(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            } else {
                providersService.modify(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            }

            int numPurchase = this.add(connection, dtoSupplier.getNit(), totalPaid);

            for (DTOProductCreate dtoProduct : products) {
                int productId;
                Optional<DTOProductDetails> dtoProductFound = productService.findByName(connection, dtoProduct.getDescription());

                if (dtoProductFound.isEmpty()) {
                    productId = productService.add(connection, dtoProduct.getDescription(), dtoProduct.getPurchasePrice(),
                            dtoProduct.getSalePrice());
                } else {
                    DTOProductDetails product = dtoProductFound.get();

                    productService.modify(connection, dtoProduct.getDescription(), product.getAmount(),
                            dtoProduct.getSalePrice(), dtoProduct.getPurchasePrice(), product.getId());

                    productId = product.getId();
                }

                purchaseDetailsService.add(connection, numPurchase, productId, dtoProduct.getAmount(),
                        dtoProduct.getPurchasePrice());
            }

            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                connection.rollback();
            }

            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private int add(Connection connection, int providerId, BigDecimal totalPaid) throws DAOException {
        DTOPurchaseCreate dto = new DTOPurchaseCreate();
        dto.setProviderId(providerId);
        dto.setTotalPurchase(totalPaid);
        dto.setCompanyId(companyId);

        return daoPurchases.add(connection, dto);
    }

    @Override
    public List<DTOPurchaseDetails> findById(int id) throws DAOException {
        return daoPurchases.findById(id, companyId);
    }

    @Override
    public List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException {
        return daoPurchases.findById(connection, id, companyId);
    }

    @Override
    public List<DTOPurchaseDetails> findByAll() throws DAOException {
        return daoPurchases.findByAll(companyId);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoPurchases.delete(id, companyId);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoPurchases.countRecords(companyId);
    }

}
