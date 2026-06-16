package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.ControllerSalesReport;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.SaleFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReportBuilder {

    private SalesReport salesReport;
    private Subject subject;
    private SaleFacade daoSales;
    private ProductsFacade daoProducts;

    public ControllerSalesReportBuilder withViewSalesReport(SalesReport salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerSalesReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerSalesReportBuilder withDaoSales(SaleFacade daoSales) {
        this.daoSales = daoSales;
        return this;
    }

    public ControllerSalesReportBuilder withDaoProducts(ProductsFacade daoProducts) {
        this.daoProducts = daoProducts;
        return this;
    }

    public ControllerSalesReport build() {
        return new ControllerSalesReport(this);
    }

    public SalesReport getSalesReport() {
        return salesReport;
    }

    public Subject getSubject() {
        return subject;
    }

    public SaleFacade getDaoSales() {
        return daoSales;
    }

    public ProductsFacade getDaoProducts() {
        return daoProducts;
    }

}
