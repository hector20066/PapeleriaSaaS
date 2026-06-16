package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.controller.builder.ControllerContentBuilder;
import com.hector.papeleriasaas.executor.*;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.Content;
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
public class ControllerContent {

    private final Color color1 = new Color(60, 73, 89);
    private final Color color2 = new Color(42, 49, 64);

    private final Content content;
    private final Dashboard dashboard;
    private final PurchaseProducts purchaseProducts;
    private final SalesManagement salesManagement;
    private final SalesReport salesReport;
    private final DebtReport debtReport;
    private final PurchasingReport purchasingReport;
    private final Customers customers;
    private final Suppliers suppliers;
    private final Coordinator coordinator;

    private boolean expandedMenu = true;
    private Timer animation;

    public ControllerContent(ControllerContentBuilder builder) {
        this.content = builder.getContent();
        this.dashboard = builder.getDashboard();
        this.coordinator = builder.getCoordinator();
        this.purchaseProducts = builder.getPurchaseProducts();
        this.salesManagement = builder.getSalesManagement();
        this.salesReport = builder.getSalesReport();
        this.debtReport = builder.getDebtReport();
        this.customers = builder.getCustomers();
        this.suppliers = builder.getSuppliers();
        this.purchasingReport = builder.getPurchasingReport();

        content.getBtn_dashboard().setBackground(color1);
        connectEvents();
    }

    private void connectEvents() {
        content.setControllerMenu(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                menu();
            }
        });

        content.setControllerDashboard(e -> showDashboard());
        content.setControllerManageProduct(e -> showPurchaseProducts());
        content.setControllerSales(e -> showSalesManagement());
        content.setControllerSalesReport(e -> showSalesReport());
        content.setControllerDebtsReport(e -> showDebtDeport());
        content.setControllerPurchaseReport(e -> showPurchasingReport());
        content.setControllerCustomers(e -> showCustomers());
        content.setControllerSuppliers(e -> showSuppliers());
        content.setControllerExit(e -> exit());
    }

    private void menu() {
        try {
            int initialWidth = content.getJpl_menu().getWidth();
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

                    content.getJpl_menu().setPreferredSize(new Dimension(currentWidth, content.getJpl_menu().getHeight()));
                    content.getJpl_menu().revalidate();
                    content.getJpl_menu().repaint();
                }
            });

            animation.start();
        } catch (Exception e) {
            MessageUtils.errorMessage("No fue posible cambiar el estado del menú.", null);
        }
    }

    private void updateTexts(boolean expandedMenu) {
        content.getBtn_dashboard().setText(expandedMenu ? "  Dashboard" : "");
        content.getBtn_products().setText(expandedMenu ? "  Realizar Compra" : "");
        content.getBtn_sales().setText(expandedMenu ? "  Realizar Venta" : "");
        content.getBtn_debtsReports().setText(expandedMenu ? "  Reporte Deudas" : "");
        content.getBtn_salesReport().setText(expandedMenu ? "  Reporte Ventas" : "");
        content.getBtn_purchaseReport().setText(expandedMenu ? "  Reporte Compras" : "");
        content.getBtn_exit().setText(expandedMenu ? "  Salir" : "");
        content.getJlb_title().setText(expandedMenu ? "PAPELERIA" : "");
    }

    private void showDashboard() {
        content.getBtn_dashboard().setBackground(color1);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(dashboard);
        //coordinator.loadData();
    }

    private void showPurchaseProducts() {
        content.getBtn_products().setBackground(color1);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(purchaseProducts);
    }

    private void showSalesManagement() {
        content.getBtn_sales().setBackground(color1);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(salesManagement);
    }

    private void showSalesReport() {
        content.getBtn_salesReport().setBackground(color1);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(salesReport);
        salesReport.getTxt_searchSale().setText("Buscar Venta");
    }

    private void showDebtDeport() {
        content.getBtn_debtsReports().setBackground(color1);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(debtReport);
        debtReport.getTxt_searchCustomer().setText("Buscar Cliente");
    }

    private void showPurchasingReport() {
        content.getBtn_purchaseReport().setBackground(color1);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);
        content.getBtn_customers().setBackground(color2);

        NavigatorUtils.showPanels(purchasingReport);
        purchasingReport.getTxt_search().setText("Buscar Compra");
    }

    private void showCustomers() {
        content.getBtn_customers().setBackground(color1);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);
        content.getBtn_suppliers().setBackground(color2);

        NavigatorUtils.showPanels(customers);
        customers.getTxt_search().setText("Buscar Cliente");
    }

    private void showSuppliers() {
        content.getBtn_suppliers().setBackground(color1);
        content.getBtn_customers().setBackground(color2);
        content.getBtn_purchaseReport().setBackground(color2);
        content.getBtn_debtsReports().setBackground(color2);
        content.getBtn_salesReport().setBackground(color2);
        content.getBtn_dashboard().setBackground(color2);
        content.getBtn_sales().setBackground(color2);
        content.getBtn_products().setBackground(color2);

        NavigatorUtils.showPanels(suppliers);
        suppliers.getTxt_search().setText("Buscar proveedor");
    }

    private void exit() {
        ReportExecutor.shutdown();
        ObserverExecutor.shutdown();
        BackgroundExecutor.shutdown();
        System.exit(0);
    }

}
