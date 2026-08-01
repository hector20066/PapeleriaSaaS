package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

import java.awt.*;

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
        createAccountView.setControllerShowSelectPlan(e -> showSelectPlan());
        createAccountView.setControllerShowSummary(e -> showSummary());
        createAccountView.setControllerShowUserInfo(e -> showUserInfo());
        createAccountView.setControllerBackUserInfo(e -> backUserInfo());
        createAccountView.setControllerBackCompanyInfo(e -> backCompanyInfo());
        createAccountView.setControllerBackSelectPlan(e -> backSelectPlan());
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

    private void showSelectPlan() {
        actionAfter();
    }

    private void showSummary() {
        actionAfter();
    }

    private void showUserInfo() {
        actionAfter();
    }

    private void backUserInfo() {
        actionPrevious();
    }

    private void backCompanyInfo() {
        actionPrevious();
    }

    private void backSelectPlan() {
        actionPrevious();
    }

    private void actionAfter() {
        CardLayout card = (CardLayout) this.createAccountView.getJpl_content().getLayout();
        card.next(createAccountView.getJpl_content());
    }

    private void actionPrevious() {
        CardLayout card = (CardLayout) createAccountView.getJpl_content().getLayout();
        card.previous(createAccountView.getJpl_content());
    }

}
