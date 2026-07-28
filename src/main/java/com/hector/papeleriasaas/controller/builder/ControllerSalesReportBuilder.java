package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesReport;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.SaleService;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReportView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReportBuilder {

    private SalesReportView salesReport;
    private Subject subject;

    private SaleService saleService;
    private ProductService productService;

    public ControllerSalesReportBuilder withViewSalesReport(SalesReportView salesReport) {
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

    public SalesReportView getSalesReport() {
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
