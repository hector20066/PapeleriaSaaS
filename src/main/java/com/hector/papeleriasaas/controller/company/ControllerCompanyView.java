package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.model.dto.companypayments.DTOPaymentCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.CompanyPaymentsService;
import com.hector.papeleriasaas.service.interfaces.UserService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.view.content.panels.companies.CompanyView;

import javax.swing.*;
import java.util.List;

public class ControllerCompanyView implements Observer {

    private final CompanyView companyView;
    private final Session session;
    private final Subject subject;

    private final UserService userService;
    private final CompanyPaymentsService companyPaymentsService;

    public ControllerCompanyView(CompanyView companyView, Session session, Subject subject, UserService userService,
                                 CompanyPaymentsService companyPaymentsService) {
        this.companyView = companyView;
        this.session = session;
        this.subject = subject;

        this.userService = userService;
        this.companyPaymentsService = companyPaymentsService;

        this.subject.register(this);
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOUserDetails> listUsers = userService.findByAllCompany();
                List<DTOPaymentCompanyDetails> paymentsList = companyPaymentsService.findByCompany(session.getCompanyId());

                SwingUtilities.invokeLater(() -> {
                    companyView.loadData(session.getCompany());
                    loadData(listUsers, paymentsList);
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("Ha ocurrido un error al cargar la información.\nError: " + e.getMessage(), companyView);
                });
            }
        });
    }

    private void loadData(List<DTOUserDetails> listUsers, List<DTOPaymentCompanyDetails> paymentsList) {
        companyView.getPaymentsList().setRowCount(0);
        companyView.getUsersList().setRowCount(0);

        listUsers.forEach(companyView::loadUsers);
        paymentsList.forEach(companyView::loadPayments);
    }

}
