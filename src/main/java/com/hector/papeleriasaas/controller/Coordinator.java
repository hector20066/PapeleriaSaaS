package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.controller.builder.*;
import com.hector.papeleriasaas.controller.company.ControllerCustomer;
import com.hector.papeleriasaas.controller.company.ControllerPurchasingReport;
import com.hector.papeleriasaas.controller.company.ControllerSuppliers;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.database.ConnectionProviderImpl;
import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.model.dao.impl.*;
import com.hector.papeleriasaas.model.dao.interfaces.*;
import com.hector.papeleriasaas.observer.ConcreteSubject;
import com.hector.papeleriasaas.service.impl.*;
import com.hector.papeleriasaas.service.interfaces.*;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.Content;
import com.hector.papeleriasaas.view.content.panels.companies.*;

import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Coordinator {

    // Observer
    private ConcreteSubject subject;

    // Views
    private Content content;
    private Dashboard dashboard;
    private Customers customers;
    private DebtReport debtReport;
    private Inventory inventory;
    private PurchaseProducts purchaseProducts;
    private PurchasingReport purchasingReport;
    private SalesManagement salesManagement;
    private SalesReport salesReport;
    private Suppliers suppliers;
    private ModifyProduct modifyProduct;

    // Connection
    private final DataBasePool dataBase = new DataBasePool();
    private final ConnectionProvider connectionProvider = new ConnectionProviderImpl(dataBase);

    // DAO's
    private final DAOCustomers daoCustomers = new DAOCustomersImpl(connectionProvider);
    private final DAODebts daoDebts = new DAODebtsImpl(connectionProvider);
    private final DAOInvoices daoInvoices = new DAOInvoicesImpl();
    private final DAOProducts daoProducts = new DAOProductsImpl(connectionProvider);
    private final DAOProviders daoProviders = new DAOProvidersImpl(connectionProvider);
    private final DAOPurchases daoPurchases = new DAOPurchasesImpl(connectionProvider);
    private final DAOPurchaseDetails daoPurchaseDetails = new DAOPurchaseDetailsImpl();
    private final DAOSales daoSales = new DAOSalesImpl(connectionProvider);
    private final DAOSaleDetails daoSaleDetails = new DAOSaleDetailsImpl();

    // Services
    private final CustomerService customerService = new CustomerServiceImpl(daoCustomers);
    private final DebtsService debtsService = new DebtsServiceImpl(daoDebts);
    private final InvoicesService invoicesService = new InvoicesServiceImpl(daoInvoices);
    private final ProductService productService = new ProductServiceImpl(daoProducts);
    private final ProvidersService providersService = new ProvidersServiceImpl(daoProviders);
    private final PurchaseDetailsService purchaseDetailsService = new PurchaseDetailsServiceImpl(daoPurchaseDetails);
    private final PurchaseService purchaseService = new PurchaseServiceImpl(daoPurchases, providersService, productService, purchaseDetailsService, connectionProvider);
    private final SaleDetailsService saleDetailsService = new SaleDetailsServiceImpl(daoSaleDetails);
    private final SaleService saleService = new SaleServiceImpl(daoSales, customerService, saleDetailsService, invoicesService, productService, connectionProvider);

    public void startApp() {
        subject = ConcreteSubject.getInstance();
        startViews();
        startDrivers();

        EventQueue.invokeLater(() -> {
            content.setVisible(true);
            content.setLocationRelativeTo(null);
            // loadData();
            NavigatorUtils.showPanels(dashboard);
        });
    }

    private void startViews() {
        content = new Content();
        dashboard = new Dashboard();
        customers = new Customers();
        debtReport = new DebtReport();
        inventory = new Inventory();
        purchaseProducts = new PurchaseProducts();
        purchasingReport = new PurchasingReport();
        salesManagement = new SalesManagement();
        salesReport = new SalesReport();
        suppliers = new Suppliers();
        modifyProduct = new ModifyProduct();
    }

    private void startDrivers() {
        new ControllerContentBuilder()
                .withViewContent(content)
                .withViewDashboard(dashboard)
                .withViewPurchaseProducts(purchaseProducts)
                .withViewSalesManagement(salesManagement)
                .withViewSalesReport(salesReport)
                .withViewPurchasingReport(purchasingReport)
                .withViewDebtReport(debtReport)
                .withViewCustomers(customers)
                .withViewSuppliers(suppliers)
                .withCoordinator(this)
                .build();

        new ControllerInventoryBuilder()
                .withViewInventory(inventory)
                .withViewPurchaseProducts(purchaseProducts)
                .withViewModifyProducts(modifyProduct)
                .withSubject(subject)
                .withProductService(productService)
                .build();

        new ControllerDashboardBuilder()
                .withViewDashboard(dashboard)
                .withSubject(subject)
                .withProductService(productService)
                .withSaleService(saleService)
                .build();

        new ControllerDebtReportBuilder()
                .withViewDebtReport(debtReport)
                .withSubject(subject)
                .withDebtsService(debtsService)
                .withSaleService(saleService)
                .build();

        new ControllerModifyProductsBuilder()
                .withViewModifyProduct(modifyProduct)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withProductService(productService)
                .build();

        new ControllerPurchaseProductsBuilder()
                .withViewProductManagement(purchaseProducts)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withPurchaseService(purchaseService)
                .build();

        new ControllerSalesManagementBuilder()
                .withViewSalesManagement(salesManagement)
                .withSubject(subject)
                .withProductService(productService)
                .withSaleService(saleService)
                .build();

        new ControllerSalesReportBuilder()
                .withViewSalesReport(salesReport)
                .withSubject(subject)
                .withSaleService(saleService)
                .withProductService(productService)
                .build();

        new ControllerPurchasingReport(purchasingReport, subject, purchaseService);
        new ControllerCustomer(customers, subject, customerService);
        new ControllerSuppliers(suppliers, subject, providersService);
    }

    public void loadData() {
        subject.notifyObservers();
    }

}
