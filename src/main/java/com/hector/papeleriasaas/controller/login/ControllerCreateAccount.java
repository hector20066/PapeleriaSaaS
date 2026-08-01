package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

public class ControllerCreateAccount {

    private final CreateAccountView createAccountView;
    private final IndexView indexView;
    private final LoginView loginView;

    public ControllerCreateAccount(CreateAccountView createAccountView, IndexView indexView, LoginView loginView) {
        this.createAccountView = createAccountView;
        this.indexView = indexView;
        this.loginView = loginView;

        connectEvents();
    }

    private void connectEvents() {
        createAccountView.setControllerRegisterCompany(e -> registerCompany());
        createAccountView.setControllerBack(e -> back());
    }

    private void registerCompany() {

    }

    private void back() {
        String anteriorView = createAccountView.getAnteriorView();

        if (anteriorView.equals("Index")) {
            NavigatorUtils.showPanels(ContentView.getJpl_content(), indexView);
        } else {
            if (anteriorView.equals("Login")) {
                NavigatorUtils.showPanels(ContentView.getJpl_content(), loginView);
            }
        }
    }

}
