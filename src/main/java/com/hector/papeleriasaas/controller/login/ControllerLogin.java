package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.controller.Coordinator;
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
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

import javax.swing.*;

public class ControllerLogin {

    private final LoginView loginView;
    private final CreateAccountView createAccountView;
    private final Coordinator coordinator;

    private final Login login;
    private final Session session;

    public ControllerLogin(LoginView loginView, CreateAccountView createAccountView, Coordinator coordinator, Login login, Session session) {
        this.loginView = loginView;
        this.createAccountView = createAccountView;
        this.coordinator = coordinator;
        this.login = login;
        this.session = session;

        connectEvents();
    }

    private void connectEvents() {
        loginView.setControllerLogin(e -> login());
        loginView.setControllerRegister(e -> register());
        loginView.setControllerSeePasswd(e -> seePassword());
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
            int remember = JOptionPane.showConfirmDialog(null, "¿Deseas guardar la sesión?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            doInBackground(user, passwd, nit, remember);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), loginView);
        }
    }

    private void doInBackground(String user, String passwd, Integer nit, int remember) {
        BackgroundExecutor.execute(() -> {
            try {
                login.login(user, passwd, nit, remember);
                DTOUserDetails dtoUser = session.getUser();

                switch (dtoUser.getRole()) {
                    case Roles.ROOT -> coordinator.startAdminEnvironment();
                    case Roles.ADMIN, Roles.EMPLOYEE -> coordinator.startCompaniesEnvironment();
                }
            } catch (DAOException | AuthException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage(e.getMessage(), loginView);
                });
            }
        });
    }

    private void register() {
        NavigatorUtils.showPanels(ContentView.getJpl_content(), createAccountView);
        createAccountView.setAnteriorView("Login");
    }

    private void seePassword() {
        if (loginView.getRbn_seePasswd().isSelected()) {
            loginView.getJpwd_password().setEchoChar((char) 0);
        } else {
            loginView.getJpwd_password().setEchoChar('•');
        }
    }

}
