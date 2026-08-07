package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.SaleDetailsNotFoundException;
import com.hector.papeleriasaas.exception.dao.persistence.SalePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSales;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleUpdate;
import com.hector.papeleriasaas.service.interfaces.*;
import org.apache.batik.css.engine.value.svg12.ICCNamedColor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SaleServiceImpl implements SaleService {

    private final DAOSales daoSales;
    private final CustomerService customerService;
    private final SaleDetailsService saleDetailsService;
    private final InvoicesService invoicesService;
    private final ProductService productService;
    private final ConnectionProvider connectionProvider;

    private final Session session;

    public SaleServiceImpl(DAOSales daoSales, CustomerService customerService, SaleDetailsService saleDetailsService, InvoicesService invoicesService, ProductService productService, ConnectionProvider connectionProvider, Session session) {
        this.daoSales = daoSales;
        this.customerService = customerService;
        this.saleDetailsService = saleDetailsService;
        this.invoicesService = invoicesService;
        this.productService = productService;
        this.connectionProvider = connectionProvider;
        this.session = session;
    }

    @Override
    public int registerSale(Integer customerIdentification, String customerName, String customerPhone, BigDecimal amountPaid,
                            BigDecimal totalSale, List<DTOSaleDetailsCreate> products) throws SQLException, DAOException {
        Connection connection = null;
        int invoiceNumber;

        try {
            if (amountPaid.signum() < 0) {
                throw new SalePersistenceException("El valor pagado no puede ser negativo.");
            }

            if (totalSale.signum() <= 0) {
                throw new SalePersistenceException("El total de la venta no puede ser menor o igual a 0");
            }

            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            Integer customerId = null;

            // Only process customer info if customerId is not null (sale with debt)
            if (customerIdentification != null) {
                DTOCustomerDetails dtoCustomerDetails = customerService.findById(connection, customerIdentification);

                if (dtoCustomerDetails == null) {
                    customerId = customerService.add(connection, customerIdentification, customerName, customerPhone);
                } else {
                    customerService.modify(connection, customerPhone, customerIdentification);
                    customerId = dtoCustomerDetails.getId();
                }
            }

            int saleId = this.add(connection, customerId, amountPaid, totalSale);

            for (DTOSaleDetailsCreate product : products) {
                saleDetailsService.add(connection, saleId, product.getProductId(), product.getAmount(), product.getUnitPrice());
            }

            invoiceNumber = invoicesService.add(connection, new DTOInvoiceCreate(saleId, 1));

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

    private int add(Connection connection, Integer customerId, BigDecimal totalPaid, BigDecimal totalSale) throws DAOException {
        DTOSaleCreate dto = new DTOSaleCreate();
        dto.setCustomerId(customerId);
        dto.setTotalPaid(totalPaid);
        dto.setTotalSale(totalSale);
        dto.setCompanyId(session.getCompanyId());

        return daoSales.add(connection, dto);
    }

    @Override
    public String checkLowStock() throws DAOException {
        StringBuilder productsOutOfStock = new StringBuilder();
        List<DTOProductDetails> products = productService.findByAll();

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

    @Override
    public List<DTOSaleDetails> findById(int id) throws DAOException {
        List<DTOSaleDetails> list = daoSales.findById(id, session.getCompanyId());

        if (list.isEmpty()) {
            throw new SaleDetailsNotFoundException(String.valueOf(id));
        }

        return list;
    }

    @Override
    public List<DTOSaleDetails> findById(Connection connection, int id) throws DAOException {
        return daoSales.findById(connection, id, session.getCompanyId());
    }

    @Override
    public List<DTOSaleDetails> findByAll() throws DAOException {
        return daoSales.findByAll(session.getCompanyId());
    }

    @Override
    public void modify(BigDecimal currentBalance, BigDecimal currentDebt, BigDecimal pass, int numSale) throws DAOException {
        if (pass.signum() <= 0) {
            throw new SalePersistenceException("El abono debe ser mayor que cero.");
        }

        if (pass.compareTo(currentDebt) > 0) {
            throw new SalePersistenceException("El abono no puede ser mayor que la deuda actual.");
        }

        DTOSaleUpdate dto = new DTOSaleUpdate();
        dto.setTotalPaid(currentBalance.add(pass));
        dto.setNumSale(numSale);
        dto.setCompanyId(session.getCompanyId());

        daoSales.modify(dto);
    }

    @Override
    public void modify(Connection connection, BigDecimal totalPaid, int numSale) throws DAOException {
        DTOSaleUpdate dto = new DTOSaleUpdate();
        dto.setTotalPaid(totalPaid);
        dto.setNumSale(numSale);
        dto.setCompanyId(session.getCompanyId());

        daoSales.modify(connection, dto);
    }

    @Override
    public int countRecords() throws DAOException {
        return daoSales.countRecords(session.getCompanyId());
    }

}
