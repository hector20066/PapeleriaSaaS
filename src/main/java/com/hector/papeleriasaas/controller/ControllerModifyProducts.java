package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.controller.builder.ControllerModifyProductsBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.*;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.utils.*;
import com.hector.papeleriasaas.view.content.panels.companies.Inventory;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProduct;

import javax.swing.*;
import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerModifyProducts {

    private final ModifyProduct modifyProduct;
    private final Inventory inventory;
    private final Subject subject;

    private final ProductsFacade daoProducts;

    public ControllerModifyProducts(ControllerModifyProductsBuilder builder) {
        this.modifyProduct = builder.getModifyProduct();
        this.inventory = builder.getInventory();
        this.subject = builder.getSubject();

        this.daoProducts = builder.getDaoProducts();

        connectEvents();
    }

    private void connectEvents() {
        modifyProduct.setControllerModify(e -> modify());
    }

    private void modify() {
        try {
            String id = modifyProduct.getTxt_id().getText().trim();
            String product = modifyProduct.getTxt_product().getText().trim();
            String amount = modifyProduct.getTxt_amount().getText().trim();
            String purchasePrice = modifyProduct.getTxt_purchasePrice().getText().trim();
            String salePrice = modifyProduct.getTxt_salePrice().getText().trim();

            if (ValidationUtils.isEmptyFields(id, product, amount, purchasePrice, salePrice)) {
                throw new EmptyFieldException("datos del producto");
            }

            if (!ValidationUtils.isNumeric(amount) || !ValidationUtils.isNumeric(id)) {
                throw new InvalidNumberInputException("ID y cantidad");
            }

            int productId = ParserUtils.parseInteger(id, "id del producto");
            int productAmount = ParserUtils.parseInteger(amount, "cantidad disponible");

            if (productAmount < 0) {
                throw new InvalidInputException("La cantidad disponible no puede ser negativa.");
            }

            BigDecimal purchasePriceValue = ParserUtils.parseBigDecimal(purchasePrice, "precio de compra");
            BigDecimal salePriceValue = ParserUtils.parseBigDecimal(salePrice, "precio de venta");

            if (purchasePriceValue.signum() < 0 || salePriceValue.signum() < 0) {
                throw new InvalidInputException("Los precios no pueden ser negativos.");
            }

            prepareUI();
            doInBackground(productId, product, productAmount, purchasePriceValue, salePriceValue);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), modifyProduct);
        }
    }

    private void prepareUI() {
        modifyProduct.getBtn_modify().setText("Modificando...");
        modifyProduct.getBtn_modify().setEnabled(false);
    }

    private void doInBackground(int id, String product, int amount, BigDecimal purchasePrice, BigDecimal salePrice) {
        BackgroundExecutor.execute(() -> {
            try {
                daoProducts.modify(product, amount, salePrice, purchasePrice, id);

                SwingUtilities.invokeLater(() -> {
                    MessageUtils.informationMessage("Producto actualizado correctamente.", modifyProduct);
                    subject.notifyObservers();
                    resetUI();
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible actualizar el producto. " + MessageUtils.userMessage(e),
                            modifyProduct);
                    resetUI();
                });
            }
        });
    }

    private void resetUI() {
        modifyProduct.getTxt_id().setText("");
        modifyProduct.getTxt_product().setText("");
        modifyProduct.getTxt_amount().setText("");
        modifyProduct.getTxt_purchasePrice().setText("");
        modifyProduct.getTxt_salePrice().setText("");

        modifyProduct.getBtn_modify().setText("Modificar");
        modifyProduct.getBtn_modify().setEnabled(true);

        NavigatorUtils.showPanels(inventory);
    }

}
