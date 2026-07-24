package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
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
    private final ProvidersServiceImpl providersFacade;
    private final ProductServiceImpl productsFacade;
    private final PurchaseDetailsServiceImpl purchaseDetailsFacade;
    private final ConnectionProvider connectionProvider;

    private int companyId = 0;

    public PurchaseServiceImpl(DAOPurchases daoPurchases, ProvidersServiceImpl providersFacade, ProductServiceImpl productsFacade, PurchaseDetailsServiceImpl purchaseDetailsFacade, ConnectionProvider connectionProvider) {
        this.daoPurchases = daoPurchases;
        this.providersFacade = providersFacade;
        this.productsFacade = productsFacade;
        this.purchaseDetailsFacade = purchaseDetailsFacade;
        this.connectionProvider = connectionProvider;
    }

    public void registerPurchase(DTOProvider dtoSupplier, BigDecimal totalPaid,
                                 List<DTOProductCreate> products) throws SQLException, DAOException {
        Connection connection = null;

        try {
            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            if (providersFacade.findById(connection, dtoSupplier.getNit()).isEmpty()) {
                providersFacade.add(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            } else {
                providersFacade.modify(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            }

            int numPurchase = this.add(connection, dtoSupplier.getNit(), totalPaid);

            for (DTOProductCreate dtoProduct : products) {
                int productId;
                Optional<DTOProductDetails> dtoProductFound = productsFacade.findByName(connection, dtoProduct.getDescription());

                if (dtoProductFound.isEmpty()) {
                    productId = productsFacade.add(connection, dtoProduct.getDescription(), dtoProduct.getPurchasePrice(),
                            dtoProduct.getSalePrice());
                } else {
                    DTOProductDetails product = dtoProductFound.get();

                    productsFacade.modify(connection, dtoProduct.getDescription(), product.getAmount(),
                            dtoProduct.getSalePrice(), dtoProduct.getPurchasePrice(), product.getId());

                    productId = product.getId();
                }

                purchaseDetailsFacade.add(connection, numPurchase, productId, dtoProduct.getAmount(),
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

    public List<DTOPurchaseDetails> findById(int id) throws DAOException {
        return daoPurchases.findById(id, companyId);
    }

    public List<DTOPurchaseDetails> findById(Connection connection, int id) throws DAOException {
        return daoPurchases.findById(connection, id, companyId);
    }

    public List<DTOPurchaseDetails> findByAll() throws DAOException {
        return daoPurchases.findByAll(companyId);
    }

    public void delete(int id) throws DAOException {
        daoPurchases.delete(id, companyId);
    }

    public int countRecords() throws DAOException {
        return daoPurchases.countRecords(companyId);
    }

}
