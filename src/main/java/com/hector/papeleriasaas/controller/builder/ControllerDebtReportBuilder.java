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
    private DebtsFacade daoDebts;
    private SalesFacade daoSales;

    public ControllerDebtReportBuilder withViewDebtReport(DebtReport debtReport) {
        this.debtReport = debtReport;
        return this;
    }

    public ControllerDebtReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDebtReportBuilder withDaoDebts(DebtsFacade daoDebts) {
        this.daoDebts = daoDebts;
        return this;
    }

    public ControllerDebtReportBuilder withDaoSales(SalesFacade daoSales) {
        this.daoSales = daoSales;
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

    public DebtsFacade getDaoDebts() {
        return daoDebts;
    }

    public SalesFacade getDaoSales() {
        return daoSales;
    }

}
