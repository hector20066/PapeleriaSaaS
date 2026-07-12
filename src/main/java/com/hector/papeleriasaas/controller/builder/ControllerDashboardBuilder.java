package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerDashboard;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.Dashboard;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboardBuilder {

    private Dashboard dashboard;
    private Subject subject;

    private ProductsFacade productsFacade;
    private SalesFacade salesFacade;

    public ControllerDashboardBuilder withViewDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerDashboardBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDashboardBuilder withProductsFacade(ProductsFacade productsFacade) {
        this.productsFacade = productsFacade;
        return this;
    }

    public ControllerDashboardBuilder withSalesFacade(SalesFacade salesFacade) {
        this.salesFacade = salesFacade;
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

    public ProductsFacade getProductsFacade() {
        return productsFacade;
    }

    public SalesFacade getSalesFacade() {
        return salesFacade;
    }

}
