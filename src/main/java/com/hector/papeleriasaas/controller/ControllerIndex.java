package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentStartView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

public class ControllerIndex {

    private final IndexView index;
    private final LoginView loginView;

    public ControllerIndex(IndexView index, LoginView loginView) {
        this.index = index;
        this.loginView = loginView;

        connectEvents();
    }

    private void connectEvents() {
        index.setControllerLogin(e -> NavigatorUtils.showPanels(ContentStartView.getJpl_content(), loginView));
    }

}
