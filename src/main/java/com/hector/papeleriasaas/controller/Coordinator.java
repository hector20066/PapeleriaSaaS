package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.controller.builder.*;
import com.hector.papeleriasaas.facade.impl.*;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.observer.ConcreteSubject;
import com.hector.papeleriasaas.service.PurchaseService;
import com.hector.papeleriasaas.service.SaleService;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.Content;
import com.hector.papeleriasaas.view.content.panels.*;

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

    private final ProductsFacade daoProducts = new ProductImplFacade();
    private final SaleFacade daoSales = new SaleImplFacade();
    private final DebtsFacade daoDebts = new DebtsImplFacade();
    private final PurchaseFacade daoPurchases = new PurchaseImplFacade();
    private final CustomerFacade daoCustomer = new CustomerImplFacade();
    private final SupplierFacade daoSupplier = new SupplierImplFacade();

    private final SaleService saleService = new SaleService();
    private final PurchaseService purchaseService = new PurchaseService();

    public void startApp() {
        subject = ConcreteSubject.getInstance();
        startViews();
        startDrivers();

        EventQueue.invokeLater(() -> {
            content.setVisible(true);
            content.setLocationRelativeTo(null);
            loadData();
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
                .withDaoProducts(daoProducts)
                .build();

        new ControllerDashboardBuilder()
                .withViewDashboard(dashboard)
                .withSubject(subject)
                .withDaoProducts(daoProducts)
                .withDaoSales(daoSales)
                .build();

        new ControllerDebtReportBuilder()
                .withViewDebtReport(debtReport)
                .withSubject(subject)
                .withDaoDebts(daoDebts)
                .withDaoSales(daoSales)
                .build();

        new ControllerModifyProductsBuilder()
                .withViewModifyProduct(modifyProduct)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withDaoProducts(daoProducts)
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
                .withDaoProducts(daoProducts)
                .withSaleService(saleService)
                .build();

        new ControllerSalesReportBuilder()
                .withViewSalesReport(salesReport)
                .withSubject(subject)
                .withDaoSales(daoSales)
                .withDaoProducts(daoProducts)
                .build();

        new ControllerPurchasingReport(purchasingReport, subject, daoPurchases);
        new ControllerCustomer(customers, subject, daoCustomer);
        new ControllerSuppliers(suppliers, subject, daoSupplier);
    }

    public void loadData() {
        subject.notifyObservers();
    }

}
