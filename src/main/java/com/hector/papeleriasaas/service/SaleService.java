package com.hector.papeleriasaas.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.impl.*;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleService {

    private final CustomersFacade customerFacade;
    private final SalesFacade saleFacade;
    private final SaleDetailsFacade saleDetailsFacade;
    private final InvoicesFacade invoiceFacade;
    private final ProductsFacade productsFacade;

    public SaleService() {
        this.customerFacade = new CustomersImplFacade();
        this.saleFacade = new SalesImplFacade();
        this.saleDetailsFacade = new SaleDetailsImplFacade();
        this.invoiceFacade = new InvoicesImplFacade();
        this.productsFacade = new ProductImplFacade();
    }

    public int registerSale(Integer customerId, String customerName, String customerPhone, BigDecimal amountPaid,
                             BigDecimal totalSale, List<DTOSaleDetailsCreate> products) throws SQLException, DAOException {
        Connection connection = null;
        int invoiceNumber = 0;

        try {
            connection = DataBasePool.getInstance().getConnection();
            connection.setAutoCommit(false);

            // Only process customer info if customerId is not null (sale with debt)
            if (customerId != null) {
                if (customerFacade.findById(connection, customerId).isEmpty()) {
                    customerFacade.add(connection, customerId, customerName, customerPhone);
                } else {
                    customerFacade.modify(connection, customerPhone, customerId);
                }
            }

            int numSale = saleFacade.add(connection, customerId, amountPaid, totalSale);

            for (DTOSaleDetailsCreate product : products) {
                saleDetailsFacade.add(connection, numSale, product.getProductId(), product.getAmount(), product.getUnitPrice());
            }

            invoiceNumber = invoiceFacade.add(connection, new DTOInvoiceCreate(numSale, 1));

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

        return invoiceNumber;
    }

    public String checkLowStock() throws DAOException {
        StringBuilder productsOutOfStock = new StringBuilder();
        List<DTOProductDetails> products = productsFacade.findByAll();

        products.stream()
                .filter(product -> product.getAmount() < 5)
                .map(DTOProductDetails::getDescription)
                .distinct()
                .forEach(p -> productsOutOfStock.append(p).append(", "));

        if (!productsOutOfStock.isEmpty()) {
            productsOutOfStock.setLength(productsOutOfStock.length() - 2);
        }

        return productsOutOfStock.toString();
    }

}
