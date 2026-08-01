package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

public class ControllerIndex {

    private final IndexView index;
    private final LoginView loginView;
    private final CreateAccountView createAccountView;

    public ControllerIndex(IndexView index, LoginView loginView, CreateAccountView createAccountView) {
        this.index = index;
        this.loginView = loginView;
        this.createAccountView = createAccountView;

        connectEvents();
    }

    private void connectEvents() {
        index.setControllerLogin(e -> NavigatorUtils.showPanels(ContentView.getJpl_content(), loginView));
        index.setControllerRegister(e -> {
            NavigatorUtils.showPanels(ContentView.getJpl_content(), createAccountView);
            createAccountView.setAnteriorView("Index");
        });
    }

}
