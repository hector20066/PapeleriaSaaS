package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.controller.company.ControllerCompaniesMenu;
import com.hector.papeleriasaas.controller.Coordinator;
import com.hector.papeleriasaas.controller.login.ControllerLogin;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.companies.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerContentBuilder {

    private CompaniesMenu content;
    private DashboardCompanyView dashboard;
    private PurchaseProductsView purchaseProducts;
    private SalesManagementView salesManagement;
    private SalesReportView salesReport;
    private PurchasingReportView purchasingReport;
    private DebtReportView debtReport;
    private CustomersView customers;
    private ProvidersView suppliers;
    private Coordinator coordinator;
    private IndexView indexView;
    private Login login;
    private Session session;

    public ControllerContentBuilder withViewContent(CompaniesMenu content) {
        this.content = content;
        return this;
    }

    public ControllerContentBuilder withViewDashboard(DashboardCompanyView dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerContentBuilder withViewPurchaseProducts(PurchaseProductsView purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
        return this;
    }

    public ControllerContentBuilder withViewSalesManagement(SalesManagementView salesManagement) {
        this.salesManagement = salesManagement;
        return this;
    }

    public ControllerContentBuilder withViewSalesReport(SalesReportView salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerContentBuilder withViewPurchasingReport(PurchasingReportView purchasingReport) {
        this.purchasingReport = purchasingReport;
        return this;
    }

    public ControllerContentBuilder withViewDebtReport(DebtReportView debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerContentBuilder withViewCustomers(CustomersView customers) {
        this.customers = customers;
        return this;
    }

    public ControllerContentBuilder withViewSuppliers(ProvidersView suppliers) {
        this.suppliers = suppliers;
        return this;
    }

    public ControllerContentBuilder withCoordinator(Coordinator coordinador) {
        this.coordinator = coordinador;
        return this;
    }

    public ControllerContentBuilder withViewIndex(IndexView index) {
        this.indexView = index;
        return this;
    }

    public ControllerContentBuilder withLogin(Login login) {
        this.login = login;
        return this;
    }

    public ControllerContentBuilder withSession(Session session) {
        this.session = session;
        return this;
    }

    public ControllerCompaniesMenu build() {
        return new ControllerCompaniesMenu(this);
    }

    public CompaniesMenu getContent() {
        return content;
    }

    public DashboardCompanyView getDashboard() {
        return dashboard;
    }

    public PurchaseProductsView getPurchaseProducts() {
        return purchaseProducts;
    }

    public SalesManagementView getSalesManagement() {
        return salesManagement;
    }

    public SalesReportView getSalesReport() {
        return salesReport;
    }

    public PurchasingReportView getPurchasingReport() {
        return purchasingReport;
    }

    public DebtReportView getDebtReport() {
        return debtReport;
    }

    public CustomersView getCustomers() {
        return customers;
    }

    public ProvidersView getSuppliers() {
        return suppliers;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public IndexView getIndexView() {
        return indexView;
    }

    public Login getLogin() {
        return login;
    }

    public Session getSession() {
        return session;
    }

}
