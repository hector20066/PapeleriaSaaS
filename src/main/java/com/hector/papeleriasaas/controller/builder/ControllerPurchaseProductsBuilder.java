package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerPurchaseProducts;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.ProviderProductsService;
import com.hector.papeleriasaas.service.interfaces.ProvidersService;
import com.hector.papeleriasaas.service.interfaces.PurchaseService;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProductsView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerPurchaseProductsBuilder {

    private PurchaseProductsView productManagement;
    private InventoryView inventory;
    private Subject subject;

    private ProductService productService;
    private PurchaseService purchaseService;
    private ProviderProductsService providerProductsService;

    public ControllerPurchaseProductsBuilder withViewProductManagement(PurchaseProductsView productManagement) {
        this.productManagement = productManagement;
        return this;
    }

    public ControllerPurchaseProductsBuilder withViewInventory(InventoryView inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerPurchaseProductsBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerPurchaseProductsBuilder withProductService(ProductService productService) {
        this.productService = productService;
        return this;
    }

    public ControllerPurchaseProductsBuilder withPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        return this;
    }

    public ControllerPurchaseProductsBuilder withProviderProductsService(ProviderProductsService providerProductsService) {
        this.providerProductsService = providerProductsService;
        return this;
    }

    public ControllerPurchaseProducts build() {
        return new ControllerPurchaseProducts(this);
    }

    public PurchaseProductsView getProductManagement() {
        return productManagement;
    }

    public InventoryView getInventory() {
        return inventory;
    }

    public Subject getSubject() {
        return subject;
    }

    public ProductService getProductService() {
        return productService;
    }

    public PurchaseService getPurchaseService() {
        return purchaseService;
    }

    public ProviderProductsService getProviderProductsService() {
        return providerProductsService;
    }

}
