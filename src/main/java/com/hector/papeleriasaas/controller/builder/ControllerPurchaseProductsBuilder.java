package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerPurchaseProducts;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.impl.PurchaseServiceImpl;
import com.hector.papeleriasaas.view.content.panels.companies.Inventory;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProducts;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerPurchaseProductsBuilder {

    private PurchaseProducts productManagement;
    private Inventory inventory;
    private Subject subject;

    private PurchaseServiceImpl purchaseService;

    public ControllerPurchaseProductsBuilder withViewProductManagement(PurchaseProducts productManagement) {
        this.productManagement = productManagement;
        return this;
    }

    public ControllerPurchaseProductsBuilder withViewInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerPurchaseProductsBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerPurchaseProductsBuilder withPurchaseService(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
        return this;
    }

    public ControllerPurchaseProducts build() {
        return new ControllerPurchaseProducts(this);
    }

    public PurchaseProducts getProductManagement() {
        return productManagement;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Subject getSubject() {
        return subject;
    }

    public PurchaseServiceImpl getPurchaseService() {
        return purchaseService;
    }

}
