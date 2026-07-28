package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDebtReport;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.DebtsService;
import com.hector.papeleriasaas.service.interfaces.SaleService;
import com.hector.papeleriasaas.view.content.panels.companies.DebtReportView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDebtReportBuilder {

    private DebtReportView debtReport;
    private Subject subject;

    private DebtsService debtsService;
    private SaleService saleService;

    public ControllerDebtReportBuilder withViewDebtReport(DebtReportView debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerDebtReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDebtReportBuilder withDebtsService(DebtsService debtsService) {
        this.debtsService = debtsService;
        return this;
    }

    public ControllerDebtReportBuilder withSaleService(SaleService saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerDebtReport build() {
        return new ControllerDebtReport(this);
    }

    public DebtReportView getDebtReport() {
        return debtReport;
    }

    public Subject getSubject() {
        return subject;
    }

    public DebtsService getDebtsService() {
        return debtsService;
    }

    public SaleService getSaleService() {
        return saleService;
    }

}
