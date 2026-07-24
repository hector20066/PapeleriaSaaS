package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDashboard;
import com.hector.papeleriasaas.service.impl.ProductServiceImpl;
import com.hector.papeleriasaas.service.impl.SaleServiceImpl;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.Dashboard;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboardBuilder {

    private Dashboard dashboard;
    private Subject subject;

    private ProductServiceImpl productService;
    private SaleServiceImpl saleService;

    public ControllerDashboardBuilder withViewDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerDashboardBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDashboardBuilder withProductService(ProductServiceImpl productService) {
        this.productService = productService;
        return this;
    }

    public ControllerDashboardBuilder withSaleService(SaleServiceImpl salesFacade) {
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

    public ProductServiceImpl getProductService() {
        return productService;
    }

    public SaleServiceImpl getSaleService() {
        return saleService;
    }

}
