package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.ControllerContent;
import com.hector.papeleriasaas.controller.Coordinator;
import com.hector.papeleriasaas.view.content.Content;
import com.hector.papeleriasaas.view.content.panels.companies.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerContentBuilder {

    private Content content;
    private Dashboard dashboard;
    private PurchaseProducts purchaseProducts;
    private SalesManagement salesManagement;
    private SalesReport salesReport;
    private PurchasingReport purchasingReport;
    private DebtReport debtReport;
    private Customers customers;
    private Suppliers suppliers;
    private Coordinator coordinator;

    public ControllerContentBuilder withViewContent(Content content) {
        this.content = content;
        return this;
    }

    public ControllerContentBuilder withViewDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerContentBuilder withViewPurchaseProducts(PurchaseProducts purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
        return this;
    }

    public ControllerContentBuilder withViewSalesManagement(SalesManagement salesManagement) {
        this.salesManagement = salesManagement;
        return this;
    }

    public ControllerContentBuilder withViewSalesReport(SalesReport salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerContentBuilder withViewPurchasingReport(PurchasingReport purchasingReport) {
        this.purchasingReport = purchasingReport;
        return this;
    }

    public ControllerContentBuilder withViewDebtReport(DebtReport debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerContentBuilder withViewCustomers(Customers customers) {
        this.customers = customers;
        return this;
    }

    public ControllerContentBuilder withViewSuppliers(Suppliers suppliers) {
        this.suppliers = suppliers;
        return this;
    }

    public ControllerContentBuilder withCoordinator(Coordinator coordinador) {
        this.coordinator = coordinador;
        return this;
    }

    public ControllerContent build() {
        return new ControllerContent(this);
    }

    public Content getContent() {
        return content;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public PurchaseProducts getPurchaseProducts() {
        return purchaseProducts;
    }

    public SalesManagement getSalesManagement() {
        return salesManagement;
    }

    public SalesReport getSalesReport() {
        return salesReport;
    }

    public PurchasingReport getPurchasingReport() {
        return purchasingReport;
    }

    public DebtReport getDebtReport() {
        return debtReport;
    }

    public Customers getCustomers() {
        return customers;
    }

    public Suppliers getSuppliers() {
        return suppliers;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

}
