package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.impl.*;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.model.dto.DTOSupplier;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseService {

    private final SupplierFacade supplierFacade;
    private final ProductsFacade productsFacade;
    private final PurchaseFacade purchaseFacade;
    private final PurchaseDetailsFacade purchaseDetailsFacade;

    public PurchaseService() {
        this.supplierFacade = new SupplierImplFacade();
        this.productsFacade = new ProductImplFacade();
        this.purchaseFacade = new PurchaseImplFacade();
        this.purchaseDetailsFacade = new PurchaseDetailsImplFacade();
    }

    public void registerPurchase(DTOSupplier dtoSupplier, BigDecimal totalPaid,
                                 List<DTOProductCreate> products) throws SQLException, DAOException {
        Connection connection = null;

        try {
            connection = DataBasePool.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (supplierFacade.findById(connection, dtoSupplier.getNit()).isEmpty()) {
                supplierFacade.add(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            } else {
                supplierFacade.modify(connection, dtoSupplier.getNit(), dtoSupplier.getRSocial(), dtoSupplier.getAddress(),
                        dtoSupplier.getPhone(), dtoSupplier.getEmail());
            }

            int numPurchase = purchaseFacade.add(connection, dtoSupplier.getNit(), totalPaid);

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

}
