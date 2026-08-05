package com.hector.papeleriasaas.controller.login;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.mediator.loginpanels.MediatorCreateAccountView;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.CompaniesService;
import com.hector.papeleriasaas.service.interfaces.PlansService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.dto.DTOCreateAccountViewSummary;
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;

public class ControllerCreateAccount {

    private final CreateAccountView createAccountView;
    private final IndexView indexView;
    private final LoginView loginView;
    private final MediatorCreateAccountView mediatorCreateAccountView;

    private final CompaniesService companiesService;
    private final PlansService plansService;

    public ControllerCreateAccount(CreateAccountView createAccountView, IndexView indexView, LoginView loginView, CompaniesService companiesService, PlansService plansService) {
        this.createAccountView = createAccountView;
        this.indexView = indexView;
        this.loginView = loginView;
        this.mediatorCreateAccountView = new MediatorCreateAccountView(createAccountView);

        this.companiesService = companiesService;
        this.plansService = plansService;

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
        createAccountView.jrbPremiumAction(e -> mediatorCreateAccountView.onFieldChangedPlanSelect());
        createAccountView.setControllerSeePasswd(e -> seePassword());

        createAccountView.addFieldsCompanyInfo(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangedCompanyInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangedCompanyInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangedCompanyInfo();
            }
        });

        createAccountView.addFieldsUserInfo(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangeUserInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangeUserInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                mediatorCreateAccountView.onFieldChangeUserInfo();
            }
        });
    }

    private void registerCompany() {
        try {
            DTOCompanyCreate dtoCompanyCreate = getDTOCompanyCreate();
            DTOUserCreate dtoUserCreate = getDTOUserCreate();
            String planName = createAccountView.getJrb_premium().isSelected()
                    ? "Plan Premium"
                    : "";

            if (planName.isBlank()) {
                throw new InvalidInputException("Por favor seleccione el plan");
            }

            prepareUI();
            doInBackground(planName, dtoCompanyCreate, dtoUserCreate);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), createAccountView);
        }
    }

    private DTOCompanyCreate getDTOCompanyCreate() throws InvalidInputException {
        String nit = createAccountView.getTxt_companyNit().getText().trim();
        String name = createAccountView.getTxt_companyName().getText().trim();
        String address = createAccountView.getTxt_companyAddress().getText().trim();
        String phone = createAccountView.getTxt_companyPhone().getText().trim();
        String email = createAccountView.getTxt_companyEmail().getText().trim();

        if (!ValidationUtils.isNumeric(nit)) {
            throw new InvalidNumberInputException("NIT");
        }

        return new DTOCompanyCreate.Builder()
                .nit(ParserUtils.parseInteger(nit, "NIT"))
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .status(Status.ACTIVE)
                .build();
    }

    private DTOUserCreate getDTOUserCreate() throws InvalidInputException {
        String id = createAccountView.getTxt_userId().getText().trim();
        String name = createAccountView.getTxt_personName().getText().trim();
        String user = createAccountView.getTxt_username().getText().trim();
        String passwd = new String(createAccountView.getJpld_passwd().getPassword());

        if (!ValidationUtils.isNumeric(id)) {
            throw new InvalidNumberInputException("Cédula");
        }

        if (passwd.isBlank()) {
            throw new EmptyFieldException("La contraseña no puede estar vacía");
        }

        return new DTOUserCreate.Builder()
                .identification(ParserUtils.parseInteger(id, "Cédula"))
                .name(name)
                .user(user)
                .passwd(passwd)
                .role(Roles.ADMIN)
                .status(Status.ACTIVE)
                .build();
    }

    private void prepareUI() {
        createAccountView.getBtn_registerCompany().setEnabled(false);
        createAccountView.getBtn_registerCompany().setText("Registrando...");
        createAccountView.getBtn_userInfo().setEnabled(false);
    }

    private void doInBackground(String planName, DTOCompanyCreate dtoCompanyCreate, DTOUserCreate dtoUserCreate) {
        BackgroundExecutor.execute(() -> {
            try {
                companiesService.registerCompanyInfo(planName, dtoCompanyCreate, dtoUserCreate);

                SwingUtilities.invokeLater(() -> {
                    MessageUtils.informationMessage("Se ha registrado la informacion de la Papelería", createAccountView);
                    NavigatorUtils.showPanels(ContentView.getJpl_content(), loginView);
                });
            } catch (SQLException | DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("Ha ocurrido un error durante el registro.\nError: " + e.getMessage(), createAccountView);
                    resetUI();
                });
            }
        });
    }

    private void resetUI() {
        createAccountView.getBtn_registerCompany().setEnabled(true);
        createAccountView.getBtn_registerCompany().setText("Registrar Informacion");
        createAccountView.getBtn_userInfo().setEnabled(true);
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
        try {
            DTOCompanyCreate dtoCompanyCreate = getDTOCompanyCreate();
            DTOUserCreate dtoUserCreate = getDTOUserCreate();
            String planName = createAccountView.getJrb_premium().isSelected()
                    ? "Plan Premium"
                    : "";

            createAccountView.loadDataSummary(getDTOCreateAccountViewSummary(dtoCompanyCreate, dtoUserCreate, planName));
            actionAfter();
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), createAccountView);
        }
    }

    private DTOCreateAccountViewSummary getDTOCreateAccountViewSummary(DTOCompanyCreate dtoCompanyCreate, DTOUserCreate dtoUserCreate, String planName) {
        return new DTOCreateAccountViewSummary.Builder()
                .companyNit(String.valueOf(dtoCompanyCreate.getNit()))
                .companyName(dtoCompanyCreate.getName())
                .companyPhone(dtoCompanyCreate.getPhone())
                .companyAddress(dtoCompanyCreate.getAddress())
                .companyEmail(dtoCompanyCreate.getEmail())
                .planName(planName)
                .userId(String.valueOf(dtoUserCreate.getIdentification()))
                .personName(dtoUserCreate.getName())
                .username(dtoUserCreate.getUser())
                .role(dtoUserCreate.getRole().getValue())
                .build();
    }

    private void showUserInfo() {
        actionAfter();

        BackgroundExecutor.execute(() -> {
            try {
                String planName = createAccountView.getJrb_premium().isSelected()
                        ? "Plan Premium"
                        : "";

                DTOPlanDetails dto = plansService.findByName(planName);

                SwingUtilities.invokeLater(() -> {
                    BigDecimal price = dto == null ? BigDecimal.ZERO : dto.getPrice();
                    createAccountView.getJlb_planPrice().setText(price.toString());
                });
            } catch (DAOException e) {
                throw new RuntimeException(e);
            }
        });
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

    private void seePassword() {
        if (createAccountView.getRbn_seePasswd().isSelected()) {
            createAccountView.getJpld_passwd().setEchoChar((char) 0);
        } else {
            createAccountView.getJpld_passwd().setEchoChar('•');
        }
    }

}
