package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.Coordinator;
import com.hector.papeleriasaas.controller.builder.ControllerContentBuilder;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.panels.companies.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerCompaniesMenu {

    private final Color color1 = new Color(60, 73, 89);
    private final Color color2 = new Color(42, 49, 64);

    private final CompaniesMenu companiesMenu;
    private final DashboardView dashboard;
    private final PurchaseProductsView purchaseProducts;
    private final SalesManagementView salesManagement;
    private final SalesReportView salesReport;
    private final DebtReportView debtReport;
    private final PurchasingReportView purchasingReport;
    private final CustomersView customers;
    private final ProvidersView suppliers;
    private final Coordinator coordinator;

    private boolean expandedMenu = true;
    private Timer animation;

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

        companiesMenu.getBtn_dashboard().setBackground(color1);
        connectEvents();
    }

    private void connectEvents() {
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
        companiesMenu.setControllerSuppliers(e -> showSuppliers());
        companiesMenu.setControllerExit(e -> exit());
    }

    private void menu() {
        try {
            int initialWidth = companiesMenu.getJpl_menu().getWidth();
            int finalWidth = expandedMenu ? 50 : 219;
            int passed = (finalWidth - initialWidth) / 14;

            if (animation != null && animation.isRunning()) {
                return;
            }

            animation = new Timer(15, null);
            animation.addActionListener(new ActionListener() {
                int currentWidth = initialWidth;

                @Override
                public void actionPerformed(ActionEvent e) {
                    currentWidth += passed;
                    boolean endAnimation = (passed > 0 && currentWidth >= finalWidth) || (passed < 0
                            && currentWidth <= finalWidth);

                    if (endAnimation) {
                        currentWidth = finalWidth;
                        animation.stop();
                        expandedMenu = !expandedMenu;

                        updateTexts(expandedMenu);
                    }

                    companiesMenu.getJpl_menu().setPreferredSize(new Dimension(currentWidth, companiesMenu.getJpl_menu().getHeight()));
                    companiesMenu.getJpl_menu().revalidate();
                    companiesMenu.getJpl_menu().repaint();
                }
            });

            animation.start();
        } catch (Exception e) {
            MessageUtils.errorMessage("No fue posible cambiar el estado del menú.", null);
        }
    }

    private void updateTexts(boolean expandedMenu) {
        companiesMenu.getBtn_dashboard().setText(expandedMenu ? "  Dashboard" : "");
        companiesMenu.getBtn_products().setText(expandedMenu ? "  Realizar Compra" : "");
        companiesMenu.getBtn_sales().setText(expandedMenu ? "  Realizar Venta" : "");
        companiesMenu.getBtn_debtsReports().setText(expandedMenu ? "  Reporte Deudas" : "");
        companiesMenu.getBtn_salesReport().setText(expandedMenu ? "  Reporte Ventas" : "");
        companiesMenu.getBtn_purchaseReport().setText(expandedMenu ? "  Reporte Compras" : "");
        companiesMenu.getBtn_exit().setText(expandedMenu ? "  Salir" : "");
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

    private void showSuppliers() {
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

    private void exit() {
        ReportExecutor.shutdown();
        ObserverExecutor.shutdown();
        BackgroundExecutor.shutdown();
        System.exit(0);
    }

}
