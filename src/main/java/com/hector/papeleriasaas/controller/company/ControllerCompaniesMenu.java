package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.controller.ControllerContent;
import com.hector.papeleriasaas.controller.Coordinator;
import com.hector.papeleriasaas.controller.builder.ControllerContentBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.companies.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerCompaniesMenu extends ControllerContent {

    private final CompaniesMenu companiesMenu;
    private final DashboardCompanyView dashboard;
    private final PurchaseProductsView purchaseProducts;
    private final SalesManagementView salesManagement;
    private final SalesReportView salesReport;
    private final DebtReportView debtReport;
    private final PurchasingReportView purchasingReport;
    private final CustomersView customers;
    private final ProvidersView suppliers;
    private final IndexView index;
    private final Coordinator coordinator;
    private final Login login;

    public ControllerCompaniesMenu(ControllerContentBuilder builder) {
        this.companiesMenu = builder.getContent();
        this.dashboard = builder.getDashboard();
        this.coordinator = builder.getCoordinator();
        this.purchaseProducts = builder.getPurchaseProducts();
        this.salesManagement = builder.getSalesManagement();
        this.salesReport = builder.getSalesReport();
        this.debtReport = builder.getDebtReport();
        this.customers = builder.getCustomers();
        this.suppliers = builder.getSuppliers();
        this.purchasingReport = builder.getPurchasingReport();
        this.index = builder.getIndexView();
        this.login = builder.getLogin();

        companiesMenu.getBtn_dashboard().setBackground(color1);

        connectEvents();
    }

    @Override
    public void connectEvents() {
        companiesMenu.setControllerMenu(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                menu();
            }
        });

        companiesMenu.setControllerDashboard(e -> showDashboard());
        companiesMenu.setControllerManageProduct(e -> showPurchaseProducts());
        companiesMenu.setControllerSales(e -> showSalesManagement());
        companiesMenu.setControllerSalesReport(e -> showSalesReport());
        companiesMenu.setControllerDebtsReport(e -> showDebtDeport());
        companiesMenu.setControllerPurchaseReport(e -> showPurchasingReport());
        companiesMenu.setControllerCustomers(e -> showCustomers());
        companiesMenu.setControllerProviders(e -> showProviders());
        companiesMenu.setControllerLogout(e -> logout());
    }

    @Override
    public JPanel getJpl_menu() {
        return companiesMenu.getJpl_menu();
    }

    @Override
    public void updateTexts(boolean expandedMenu) {
        companiesMenu.getBtn_dashboard().setText(expandedMenu ? "  Dashboard" : "");
        companiesMenu.getBtn_products().setText(expandedMenu ? "  Realizar Compra" : "");
        companiesMenu.getBtn_sales().setText(expandedMenu ? "  Realizar Venta" : "");
        companiesMenu.getBtn_debtsReports().setText(expandedMenu ? "  Reporte Deudas" : "");
        companiesMenu.getBtn_salesReport().setText(expandedMenu ? "  Reporte Ventas" : "");
        companiesMenu.getBtn_purchaseReport().setText(expandedMenu ? "  Reporte Compras" : "");
        companiesMenu.getBtn_exit().setText(expandedMenu ? "  Cerrar Sesión" : "");
        companiesMenu.getJlb_title().setText(expandedMenu ? "PAPELERIA" : "");
    }

    private void showDashboard() {
        companiesMenu.getBtn_dashboard().setBackground(color1);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), dashboard);
        //coordinator.loadData();
    }

    private void showPurchaseProducts() {
        companiesMenu.getBtn_products().setBackground(color1);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), purchaseProducts);
    }

    private void showSalesManagement() {
        companiesMenu.getBtn_sales().setBackground(color1);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), salesManagement);
    }

    private void showSalesReport() {
        companiesMenu.getBtn_salesReport().setBackground(color1);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), salesReport);
        salesReport.getTxt_searchSale().setText("Buscar Venta");
    }

    private void showDebtDeport() {
        companiesMenu.getBtn_debtsReports().setBackground(color1);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), debtReport);
        debtReport.getTxt_searchCustomer().setText("Buscar Cliente");
    }

    private void showPurchasingReport() {
        companiesMenu.getBtn_purchaseReport().setBackground(color1);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);
        companiesMenu.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), purchasingReport);
        purchasingReport.getTxt_search().setText("Buscar Compra");
    }

    private void showCustomers() {
        companiesMenu.getBtn_customers().setBackground(color1);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);
        companiesMenu.getBtn_suppliers().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), customers);
        customers.getTxt_search().setText("Buscar Cliente");
    }

    private void showProviders() {
        companiesMenu.getBtn_suppliers().setBackground(color1);
        companiesMenu.getBtn_customers().setBackground(color2);
        companiesMenu.getBtn_purchaseReport().setBackground(color2);
        companiesMenu.getBtn_debtsReports().setBackground(color2);
        companiesMenu.getBtn_salesReport().setBackground(color2);
        companiesMenu.getBtn_dashboard().setBackground(color2);
        companiesMenu.getBtn_sales().setBackground(color2);
        companiesMenu.getBtn_products().setBackground(color2);

        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), suppliers);
        suppliers.getTxt_search().setText("Buscar proveedor");
    }

    @Override
    public void logout() {
        try {
            NavigatorUtils.showPanels(ContentView.getJpl_content(), index);
            login.logout();
        } catch (DAOException e) {
            MessageUtils.errorMessage("Ha ocurrido un error al cerrar la sesión.", companiesMenu);
        }
    }

}
