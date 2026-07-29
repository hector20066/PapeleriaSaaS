package com.hector.papeleriasaas.controller.admin;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.controller.ControllerContent;
import com.hector.papeleriasaas.controller.Coordinator;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.admin.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerAdminMenu extends ControllerContent {

    private final AdminMenu adminMenu;
    private final BusinessManagementView businessManagementView;
    private final DashboardAdminView dashboardAdminView;
    private final ManagePlansView managePlansView;
    private final PaymentHistoryView paymentHistoryView;
    private final UserManagementView userManagementView;
    private final IndexView index;
    private final Session session;
    private final Coordinator coordinator;

    public ControllerAdminMenu(AdminMenu adminMenu, BusinessManagementView businessManagementView, DashboardAdminView dashboardAdminView,
                               ManagePlansView managePlansView, PaymentHistoryView paymentHistoryView, UserManagementView userManagementView,
                               IndexView index, Session session, Coordinator coordinator) {
        this.adminMenu = adminMenu;
        this.businessManagementView = businessManagementView;
        this.dashboardAdminView = dashboardAdminView;
        this.managePlansView = managePlansView;
        this.paymentHistoryView = paymentHistoryView;
        this.userManagementView = userManagementView;
        this.index = index;
        this.session = session;
        this.coordinator = coordinator;

        connectEvents();
    }

    @Override
    public void connectEvents() {
        adminMenu.setControllerMenu(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu();
            }
        });

        adminMenu.setControllerDashboard(e -> showDashboard());
        adminMenu.setControllerCompanies(e -> showCompanies());
        adminMenu.setControllerPlans(e -> showPlans());
        adminMenu.setControllerPaymentHistory(e -> showPaymentsHistory());
        adminMenu.setControllerUsers(e -> showUsers());
        adminMenu.setControllerLogout(e -> logout());
    }

    @Override
    public void updateTexts(boolean expandedMenu) {
        adminMenu.getBtn_dashboard().setText(expandedMenu ? "  Dashboard" : "");
        adminMenu.getBtn_companies().setText(expandedMenu ? "  Empresas" : "");
        adminMenu.getBtn_plans().setText(expandedMenu ? "  Planes" : "");
        adminMenu.getBtn_paymentHistory().setText(expandedMenu ? "  Historial de pagos" : "");
        adminMenu.getBtn_users().setText(expandedMenu ? "  Usuarios" : "");
        adminMenu.getBtn_logout().setText(expandedMenu ? "  Cerrar Sesión" : "");
        adminMenu.getJlb_title().setText(expandedMenu ? "Papelería SaaS" : "");
    }

    @Override
    public JPanel getJpl_menu() {
        return adminMenu.getJpl_menu();
    }

    private void showDashboard() {
        adminMenu.getBtn_dashboard().setBackground(color1);
        adminMenu.getBtn_companies().setBackground(color2);
        adminMenu.getBtn_plans().setBackground(color2);
        adminMenu.getBtn_paymentHistory().setBackground(color2);
        adminMenu.getBtn_users().setBackground(color2);
        adminMenu.getBtn_logout().setBackground(color2);

        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), dashboardAdminView);
    }

    private void showCompanies() {
        adminMenu.getBtn_dashboard().setBackground(color2);
        adminMenu.getBtn_companies().setBackground(color1);
        adminMenu.getBtn_plans().setBackground(color2);
        adminMenu.getBtn_paymentHistory().setBackground(color2);
        adminMenu.getBtn_users().setBackground(color2);
        adminMenu.getBtn_logout().setBackground(color2);

        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), businessManagementView);
    }

    private void showPlans() {
        adminMenu.getBtn_dashboard().setBackground(color2);
        adminMenu.getBtn_companies().setBackground(color2);
        adminMenu.getBtn_plans().setBackground(color1);
        adminMenu.getBtn_paymentHistory().setBackground(color2);
        adminMenu.getBtn_users().setBackground(color2);
        adminMenu.getBtn_logout().setBackground(color2);

        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), managePlansView);
    }

    private void showPaymentsHistory() {
        adminMenu.getBtn_dashboard().setBackground(color2);
        adminMenu.getBtn_companies().setBackground(color2);
        adminMenu.getBtn_plans().setBackground(color2);
        adminMenu.getBtn_paymentHistory().setBackground(color1);
        adminMenu.getBtn_users().setBackground(color2);
        adminMenu.getBtn_logout().setBackground(color2);

        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), paymentHistoryView);
    }

    private void showUsers() {
        adminMenu.getBtn_dashboard().setBackground(color2);
        adminMenu.getBtn_companies().setBackground(color2);
        adminMenu.getBtn_plans().setBackground(color2);
        adminMenu.getBtn_paymentHistory().setBackground(color2);
        adminMenu.getBtn_users().setBackground(color1);
        adminMenu.getBtn_logout().setBackground(color2);

        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), userManagementView);
    }

    @Override
    public void logout() {
        NavigatorUtils.showPanels(ContentView.getJpl_content(), index);
        session.logout();
    }

}
