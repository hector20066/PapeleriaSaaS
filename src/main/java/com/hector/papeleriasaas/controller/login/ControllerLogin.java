package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.exception.auth.AuthException;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.panels.admin.AdminMenu;
import com.hector.papeleriasaas.view.content.panels.companies.CompaniesMenu;
import com.hector.papeleriasaas.view.content.panels.companies.DashboardView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

import javax.swing.*;

public class ControllerLogin {

    private final LoginView loginView;
    private final ContentView contentView;
    private final DashboardView dashboardView;
    private final AdminMenu adminMenu;
    private final CompaniesMenu companiesMenu;

    private final Login login;
    private final Session session;

    public ControllerLogin(LoginView loginView, ContentView contentView, DashboardView dashboardView, AdminMenu adminMenu, CompaniesMenu companiesMenu, Login login, Session session) {
        this.loginView = loginView;
        this.contentView = contentView;
        this.dashboardView = dashboardView;
        this.adminMenu = adminMenu;
        this.companiesMenu = companiesMenu;
        this.login = login;
        this.session = session;

        connectEvents();
    }

    private void connectEvents() {
        loginView.setControllerLogin(e -> login());
        loginView.setControllerRegister(e -> register());
    }

    private void login() {
        try {
            String nitStr = loginView.getTxt_nit().getText();
            String user = loginView.getTxt_user().getText();
            String passwd = new String(loginView.getJpwd_password().getPassword());

            if (user.isBlank()) {
                throw new EmptyFieldException("Por favor ingrese el usuario");
            }

            if (passwd.isBlank()) {
                throw new EmptyFieldException("Por favor ingrese su contraseña");
            }

            Integer nit = nitStr.isBlank() ? 0 : Integer.parseInt(nitStr);

            NavigatorUtils.showPanels(ContentView.getJpl_content(), companiesMenu);
            NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), dashboardView);
            // doInBackground(user, passwd, nit);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), loginView);
        }
    }

    private void doInBackground(String user, String passwd, Integer nit) {
        BackgroundExecutor.execute(() -> {
            try {
                login.login(user, passwd, nit);

                DTOUserDetails dtoUser = session.getUser();

                switch (dtoUser.getRole()) {
                    case Roles.ROOT -> {
                        IO.println("Root");
                    }
                    case Roles.ADMIN -> {
                        IO.println("admin");
                    }
                    case Roles.EMPLOYEE -> {

                    }
                }

                NavigatorUtils.showPanels(ContentView.getJpl_content(), adminMenu);
            } catch (DAOException | AuthException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage(e.getMessage(), loginView);
                });
            }
        });
    }

    private void register() {

    }

}
