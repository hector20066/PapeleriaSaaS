package com.hector.papeleriasaas.controller.builder;

import com.hector.papeleriasaas.controller.company.ControllerModifyProducts;
import com.hector.papeleriasaas.service.interfaces.ProductsFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.view.content.panels.companies.Inventory;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProduct;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerModifyProductsBuilder {

    private ModifyProduct modifyProduct;
    private Inventory inventory;
    private Subject subject;

    private ProductsFacade productsFacade;

    public ControllerModifyProductsBuilder withViewModifyProduct(ModifyProduct modifyProduct) {
        this.modifyProduct = modifyProduct;
        return this;
    }

    public ControllerModifyProductsBuilder withViewInventory(Inventory inventory) {
        this.inventory = inventory;
        return this;
    }

    public ControllerModifyProductsBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public ControllerModifyProductsBuilder withProductsFacade(ProductsFacade productsFacade) {
        this.productsFacade = productsFacade;
        return this;
    }

    public ControllerModifyProducts build() {
        return new ControllerModifyProducts(this);
    }

    public ModifyProduct getModifyProduct() {
        return modifyProduct;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Subject getSubject() {
        return subject;
    }

    public ProductsFacade getProductsFacade() {
        return productsFacade;
    }

}
