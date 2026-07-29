package com.hector.papeleriasaas.controller.admin;

import com.hector.papeleriasaas.view.content.panels.admin.*;

public class ControllerAdminMenu {

    private final AdminMenu adminMenu;
    private final BusinessManagementView businessManagementView;
    private final DashboardAdminView dashboardAdminView;
    private final ManagePlansView managePlansView;
    private final PaymentHistoryView paymentHistoryView;
    private final UserManagementView userManagementView;

    public ControllerAdminMenu(AdminMenu adminMenu, BusinessManagementView businessManagementView, DashboardAdminView dashboardAdminView,
                               ManagePlansView managePlansView, PaymentHistoryView paymentHistoryView, UserManagementView userManagementView) {
        this.adminMenu = adminMenu;
        this.businessManagementView = businessManagementView;
        this.dashboardAdminView = dashboardAdminView;
        this.managePlansView = managePlansView;
        this.paymentHistoryView = paymentHistoryView;
        this.userManagementView = userManagementView;


    }
}
