package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.controller.builder.*;
import com.hector.papeleriasaas.controller.company.ControllerCustomer;
import com.hector.papeleriasaas.controller.company.ControllerPurchasingReport;
import com.hector.papeleriasaas.controller.company.ControllerSuppliers;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.database.ConnectionProviderImpl;
import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.service.*;
import com.hector.papeleriasaas.service.interfaces.*;
import com.hector.papeleriasaas.model.dao.impl.*;
import com.hector.papeleriasaas.model.dao.interfaces.*;
import com.hector.papeleriasaas.observer.ConcreteSubject;
import com.hector.papeleriasaas.service.PurchaseService;
import com.hector.papeleriasaas.service.SaleService;
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

    // Facade's
    private final CustomersFacade customersFacade = new CustomerService(daoCustomers);
    private final DebtsFacade debtsFacade = new DebtsService(daoDebts);
    private final InvoicesFacade invoicesFacade = new InvoicesService(daoInvoices);
    private final ProductsFacade productsFacade = new ProductService(daoProducts);
    private final ProvidersFacade providersFacade = new ProvidersService(daoProviders);
    private final PurchasesFacade purchasesFacade = new PurchasesImplFacade(daoPurchases);
    private final PurchaseDetailsFacade purchaseDetailsFacade = new PurchaseDetailsService(daoPurchaseDetails);
    private final SalesFacade salesFacade = new SalesImplFacade(daoSales);
    private final SaleDetailsFacade saleDetailsFacade = new SaleDetailsService(daoSaleDetails);

    // Service
    private final SaleService saleService = new SaleService(customersFacade, salesFacade, saleDetailsFacade, invoicesFacade, productsFacade, connectionProvider);
    private final PurchaseService purchaseService = new PurchaseService(providersFacade, productsFacade, purchasesFacade, purchaseDetailsFacade, connectionProvider);

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
                .withProductsFacade(productsFacade)
                .build();

        new ControllerDashboardBuilder()
                .withViewDashboard(dashboard)
                .withSubject(subject)
                .withProductsFacade(productsFacade)
                .withSalesFacade(salesFacade)
                .build();

        new ControllerDebtReportBuilder()
                .withViewDebtReport(debtReport)
                .withSubject(subject)
                .withDebtsFacade(debtsFacade)
                .withSalesFacade(salesFacade)
                .build();

        new ControllerModifyProductsBuilder()
                .withViewModifyProduct(modifyProduct)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withProductsFacade(productsFacade)
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
                .withProductsFacade(productsFacade)
                .withSaleService(saleService)
                .build();

        new ControllerSalesReportBuilder()
                .withViewSalesReport(salesReport)
                .withSubject(subject)
                .withSalesFacade(salesFacade)
                .withProductsFacade(productsFacade)
                .build();

        new ControllerPurchasingReport(purchasingReport, subject, purchasesFacade);
        new ControllerCustomer(customers, subject, customersFacade);
        new ControllerSuppliers(suppliers, subject, providersFacade);
    }

    public void loadData() {
        subject.notifyObservers();
    }

}
