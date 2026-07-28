package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerInventory;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProductView;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProductsView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerInventoryBuilder {

    private InventoryView inventory;
    private PurchaseProductsView purchaseProducts;
    private ModifyProductView modifyProduct;
    private Subject subject;

    private ProductService productService;

    public ControllerInventoryBuilder withViewInventory(InventoryView inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerInventoryBuilder withViewPurchaseProducts(PurchaseProductsView purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
        return this;
    }

    public ControllerInventoryBuilder withViewModifyProducts(ModifyProductView modifyProduct) {
        this.modifyProduct = modifyProduct;
        return this;
    }

    public ControllerInventoryBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerInventoryBuilder withProductService(ProductService productService) {
        this.productService = productService;
        return this;
    }

    public ControllerInventory build() {
        return new ControllerInventory(this);
    }

    public Subject getSubject() {
        return subject;
    }

    public PurchaseProductsView getPurchaseProducts() {
        return purchaseProducts;
    }

    public InventoryView getInventory() {
        return inventory;
    }

    public ModifyProductView getModifyProduct() {
        return modifyProduct;
    }

    public ProductService getProductService() {
        return productService;
    }

}
