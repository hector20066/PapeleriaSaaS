package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDashboard;
import com.hector.papeleriasaas.service.ProductService;
import com.hector.papeleriasaas.service.SaleService;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.Dashboard;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboardBuilder {

    private Dashboard dashboard;
    private Subject subject;

    private ProductService productService;
    private SaleService saleService;

    public ControllerDashboardBuilder withViewDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerDashboardBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDashboardBuilder withProductService(ProductService productService) {
        this.productService = productService;
        return this;
    }

    public ControllerDashboardBuilder withSaleService(SaleService salesFacade) {
        this.saleService = salesFacade;
        return this;
    }

    public ControllerDashboard build() {
        return new ControllerDashboard(this);
    }

    public Dashboard getDashboard() {
        return dashboard;
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
