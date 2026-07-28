package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentStartView;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.panels.companies.DashboardView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

public class ControllerLogin {

    private final LoginView loginView;
    private final ContentView contentView;
    private final ContentStartView contentStartView;
    private final DashboardView dashboardView;

    private final Login login;

    public ControllerLogin(LoginView loginView, ContentView contentView, ContentStartView contentStartView, DashboardView dashboardView, Login login) {
        this.loginView = loginView;
        this.contentView = contentView;
        this.contentStartView = contentStartView;
        this.dashboardView = dashboardView;
        this.login = login;

        connectEvents();
    }

    private void connectEvents() {
        loginView.setControllerLogin(e -> login());
    }

    private void login() {
        contentView.setVisible(true);
        contentView.setLocationRelativeTo(null);

        NavigatorUtils.showPanels(ContentView.getJpl_content(), dashboardView);

        contentStartView.setVisible(false);
        contentStartView.dispose();
    }

}
