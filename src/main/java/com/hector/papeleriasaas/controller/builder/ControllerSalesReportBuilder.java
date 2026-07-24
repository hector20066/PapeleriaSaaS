package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesReport;
import com.hector.papeleriasaas.service.interfaces.ProductsFacade;
import com.hector.papeleriasaas.service.interfaces.SalesFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReportBuilder {

    private SalesReport salesReport;
    private Subject subject;

    private SalesFacade salesFacade;
    private ProductsFacade productsFacade;

    public ControllerSalesReportBuilder withViewSalesReport(SalesReport salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerSalesReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerSalesReportBuilder withSalesFacade(SalesFacade salesFacade) {
        this.salesFacade = salesFacade;
        return this;
    }

    public ControllerSalesReportBuilder withProductsFacade(ProductsFacade productsFacade) {
        this.productsFacade = productsFacade;
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

    public SalesFacade getSalesFacade() {
        return salesFacade;
    }

    public ProductsFacade getProductsFacade() {
        return productsFacade;
    }

}
