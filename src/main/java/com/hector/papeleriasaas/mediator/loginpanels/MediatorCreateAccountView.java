package com.hector.papeleriasaas.mediator.loginpanels;

import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;

public class MediatorCreateAccountView {

    private final CreateAccountView createAccountView;

    public MediatorCreateAccountView(CreateAccountView createAccountView) {
        this.createAccountView = createAccountView;
    }

    public void onFieldChangedCompanyInfo() {
        boolean allFields = !createAccountView.getTxt_companyNit().getText().isBlank()
                && !createAccountView.getTxt_companyName().getText().isBlank()
                && !createAccountView.getTxt_companyAddress().getText().isBlank()
                && !createAccountView.getTxt_companyEmail().getText().isBlank()
                && !createAccountView.getTxt_companyPhone().getText().isBlank();

        createAccountView.getBtn_showSelectPlan().setEnabled(allFields);
    }

    public void onFieldChangedPlanSelect() {
        boolean isSelected = createAccountView.getJrb_premium().isSelected();
        createAccountView.getBtn_showUserInfo().setEnabled(isSelected);
    }

    public void onFieldChangeUserInfo() {
        String passwd = new String(createAccountView.getJpld_passwd().getPassword());

        boolean allFields = !createAccountView.getTxt_personName().getText().isBlank()
                && !createAccountView.getTxt_userId().getText().isBlank()
                && !createAccountView.getTxt_username().getText().isBlank()
                && !passwd.isBlank();

        createAccountView.getBtn_showSummary().setEnabled(allFields);
    }

}
