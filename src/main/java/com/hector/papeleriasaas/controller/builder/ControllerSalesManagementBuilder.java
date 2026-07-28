package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerSalesManagement;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.SaleService;
import com.hector.papeleriasaas.view.content.panels.companies.SalesManagementView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesManagementBuilder {

    private SalesManagementView salesManagement;
    private Subject subject;

    private ProductService productService;
    private SaleService saleService;

    public ControllerSalesManagementBuilder withViewSalesManagement(SalesManagementView salesManagement) {
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

    public SalesManagementView getSalesManagement() {
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
