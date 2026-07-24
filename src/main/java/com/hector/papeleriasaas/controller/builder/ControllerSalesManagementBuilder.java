package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesManagement;
import com.hector.papeleriasaas.service.ProductService;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.SaleService;
import com.hector.papeleriasaas.view.content.panels.companies.SalesManagement;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesManagementBuilder {

    private SalesManagement salesManagement;
    private Subject subject;

    private ProductService productService;
    private SaleService saleService;

    public ControllerSalesManagementBuilder withViewSalesManagement(SalesManagement salesManagement) {
        this.salesManagement = salesManagement;
        return this;
    }

    public ControllerSalesManagementBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerSalesManagementBuilder withProductService(ProductService productService) {
        this.productService = productService;
        return this;
    }

    public ControllerSalesManagementBuilder withSaleService(SaleService saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerSalesManagement build() {
        return new ControllerSalesManagement(this);
    }

    public SalesManagement getSalesManagement() {
        return salesManagement;
    }

    public Subject getSubject() {
        return subject;
    }

    public ProductService getProductService() {
        return productService;
    }

    public SaleService getSaleService() {
        return saleService;
    }

}
