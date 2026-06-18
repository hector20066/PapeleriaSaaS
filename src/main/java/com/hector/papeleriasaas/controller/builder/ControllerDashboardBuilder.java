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
    private ProductsFacade daoProducts;
    private SalesFacade daoSales;

    public ControllerDashboardBuilder withViewDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
        return this;
    }

    public ControllerDashboardBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerDashboardBuilder withDaoProducts(ProductsFacade daoProducts) {
        this.daoProducts = daoProducts;
        return this;
    }

    public ControllerDashboardBuilder withDaoSales(SalesFacade daoSales) {
        this.daoSales = daoSales;
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

    public ProductsFacade getDaoProducts() {
        return daoProducts;
    }

    public SalesFacade getDaoSales() {
        return daoSales;
    }

}
