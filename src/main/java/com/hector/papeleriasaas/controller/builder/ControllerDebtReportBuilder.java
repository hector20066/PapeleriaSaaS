package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDebtReport;
import com.hector.papeleriasaas.service.impl.DebtsServiceImpl;
import com.hector.papeleriasaas.service.impl.SaleServiceImpl;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.DebtReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDebtReportBuilder {

    private DebtReport debtReport;
    private Subject subject;

    private DebtsServiceImpl debtsService;
    private SaleServiceImpl saleService;

    public ControllerDebtReportBuilder withViewDebtReport(DebtReport debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerDebtReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDebtReportBuilder withDebtsService(DebtsServiceImpl debtsService) {
        this.debtsService = debtsService;
        return this;
    }

    public ControllerDebtReportBuilder withSaleService(SaleServiceImpl saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerDebtReport build() {
        return new ControllerDebtReport(this);
    }

    public DebtReport getDebtReport() {
        return debtReport;
    }

    public Subject getSubject() {
        return subject;
    }

    public DebtsServiceImpl getDebtsService() {
        return debtsService;
    }

    public SaleServiceImpl getSaleService() {
        return saleService;
    }

}
