package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesReport;
import com.hector.papeleriasaas.service.ProductService;
import com.hector.papeleriasaas.service.SaleService;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReportBuilder {

    private SalesReport salesReport;
    private Subject subject;

    private SaleService saleService;
    private ProductService productService;

    public ControllerSalesReportBuilder withViewSalesReport(SalesReport salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerSalesReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerSalesReportBuilder withSaleService(SaleService saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerSalesReportBuilder withProductService(ProductService productService) {
        this.productService = productService;
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

    public SaleService getSaleService() {
        return saleService;
    }

    public ProductService getProductService() {
        return productService;
    }

}
