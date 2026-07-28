package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerModifyProductsBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.validator.ModifyProductValidator;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProductView;

import javax.swing.*;
import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerModifyProducts {

    private final ModifyProductView modifyProduct;
    private final InventoryView inventory;
    private final Subject subject;

    private final ProductService productService;

    public ControllerModifyProducts(ControllerModifyProductsBuilder builder) {
        this.modifyProduct = builder.getModifyProduct();
        this.inventory = builder.getInventory();
        this.subject = builder.getSubject();

        this.productService = builder.getProductService();

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

            ModifyProductValidator.fieldsValidation(id, product, amount, purchasePrice, salePrice);

            int productId = ParserUtils.parseInteger(id, "id del producto");
            int productAmount = ParserUtils.parseInteger(amount, "cantidad disponible");

            BigDecimal purchasePriceValue = ParserUtils.parseBigDecimal(purchasePrice, "precio de compra");
            BigDecimal salePriceValue = ParserUtils.parseBigDecimal(salePrice, "precio de venta");

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
                productService.modify(product, amount, salePrice, purchasePrice, id);

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

        NavigatorUtils.showPanels(ContentView.getJpl_content(), inventory);
    }

}
