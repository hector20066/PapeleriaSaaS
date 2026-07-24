package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesReport;
import com.hector.papeleriasaas.service.impl.ProductServiceImpl;
import com.hector.papeleriasaas.service.impl.SaleServiceImpl;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReport;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReportBuilder {

    private SalesReport salesReport;
    private Subject subject;

    private SaleServiceImpl saleService;
    private ProductServiceImpl productService;

    public ControllerSalesReportBuilder withViewSalesReport(SalesReport salesReport) {
        this.salesReport = salesReport;
        return this;
    }

    public ControllerSalesReportBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerSalesReportBuilder withSaleService(SaleServiceImpl saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerSalesReportBuilder withProductService(ProductServiceImpl productService) {
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

    public SaleServiceImpl getSaleService() {
        return saleService;
    }

    public ProductServiceImpl getProductService() {
        return productService;
    }

}
