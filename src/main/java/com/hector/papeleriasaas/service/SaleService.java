package com.hector.papeleriasaas.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.impl.CustomerImplFacade;
import com.hector.papeleriasaas.facade.impl.InvoiceImplFacade;
import com.hector.papeleriasaas.facade.impl.SaleDetailsImplFacade;
import com.hector.papeleriasaas.facade.impl.SaleImplFacade;
import com.hector.papeleriasaas.facade.interfaces.CustomerFacade;
import com.hector.papeleriasaas.facade.interfaces.InvoiceFacade;
import com.hector.papeleriasaas.facade.interfaces.SaleDetailsFacade;
import com.hector.papeleriasaas.facade.interfaces.SaleFacade;
import com.hector.papeleriasaas.model.dto.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.DTOPurchaseDetailsCreate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleService {

    private final CustomerFacade customerFacade;
    private final SaleFacade saleFacade;
    private final SaleDetailsFacade saleDetailsFacade;
    private final InvoiceFacade invoiceFacade;

    public SaleService() {
        this.customerFacade = new CustomerImplFacade();
        this.saleFacade = new SaleImplFacade();
        this.saleDetailsFacade = new SaleDetailsImplFacade();
        this.invoiceFacade = new InvoiceImplFacade();
    }

    public int registerSale(Integer customerId, String customerName, String customerPhone, BigDecimal amountPaid,
                             BigDecimal totalSale, List<DTOPurchaseDetailsCreate> products) throws SQLException, DAOException {
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

            for (DTOPurchaseDetailsCreate product : products) {
                saleDetailsFacade.add(connection, numSale, product.getProductId(), product.getAmount(), product.getUnitPrice());
            }

            invoiceNumber = invoiceFacade.add(connection, new DTOInvoiceCreate(numSale));

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

}
