package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerModifyProducts;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProductView;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerModifyProductsBuilder {

    private ModifyProductView modifyProduct;
    private InventoryView inventory;
    private Subject subject;

    private ProductService productService;

    public ControllerModifyProductsBuilder withViewModifyProduct(ModifyProductView modifyProduct) {
        this.modifyProduct = modifyProduct;
        return this;
    }

    public ControllerModifyProductsBuilder withViewInventory(InventoryView inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerModifyProductsBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerModifyProductsBuilder withProductService(ProductService productService) {
        this.productService = productService;
        return this;
    }

    public ControllerModifyProducts build() {
        return new ControllerModifyProducts(this);
    }

    public ModifyProductView getModifyProduct() {
        return modifyProduct;
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

}
