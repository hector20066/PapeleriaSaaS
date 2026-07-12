package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDebtReport;
import com.hector.papeleriasaas.facade.interfaces.DebtsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.DebtReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDebtReportBuilder {

    private DebtReport debtReport;
    private Subject subject;

    private DebtsFacade debtsFacade;
    private SalesFacade salesFacade;

    public ControllerDebtReportBuilder withViewDebtReport(DebtReport debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerDebtReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDebtReportBuilder withDebtsFacade(DebtsFacade debtsFacade) {
        this.debtsFacade = debtsFacade;
        return this;
    }

    public ControllerDebtReportBuilder withSalesFacade(SalesFacade salesFacade) {
        this.salesFacade = salesFacade;
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

    public DebtsFacade getDebtsFacade() {
        return debtsFacade;
    }

    public SalesFacade getSalesFacade() {
        return salesFacade;
    }

}
