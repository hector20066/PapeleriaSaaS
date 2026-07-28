package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDashboard;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.SaleService;
import com.hector.papeleriasaas.view.content.panels.companies.DashboardCompanyView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboardBuilder {

    private DashboardCompanyView dashboard;
    private Subject subject;

    private ProductService productService;
    private SaleService saleService;

    public ControllerDashboardBuilder withViewDashboard(DashboardCompanyView dashboard) {
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

    public ControllerDashboardBuilder withSaleService(SaleService saleService) {
        this.saleService = saleService;
        return this;
    }

    public ControllerDashboard build() {
        return new ControllerDashboard(this);
    }

    public DashboardCompanyView getDashboard() {
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
