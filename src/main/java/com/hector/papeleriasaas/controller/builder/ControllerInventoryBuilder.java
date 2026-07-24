package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerInventory;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.view.content.panels.companies.Inventory;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProduct;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProducts;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerInventoryBuilder {

    private Inventory inventory;
    private PurchaseProducts purchaseProducts;
    private ModifyProduct modifyProduct;
    private Subject subject;

    private ProductService productService;

    public ControllerInventoryBuilder withViewInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerInventoryBuilder withViewPurchaseProducts(PurchaseProducts purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
        return this;
    }

    public ControllerInventoryBuilder withViewModifyProducts(ModifyProduct modifyProduct) {
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

    public PurchaseProducts getPurchaseProducts() {
        return purchaseProducts;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ModifyProduct getModifyProduct() {
        return modifyProduct;
    }

    public ProductService getProductService() {
        return productService;
    }

}
