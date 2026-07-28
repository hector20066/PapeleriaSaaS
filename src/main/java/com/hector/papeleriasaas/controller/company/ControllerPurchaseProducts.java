package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.business.PurchaseProductCalculator;
import com.hector.papeleriasaas.controller.builder.ControllerPurchaseProductsBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.mapper.PurchaseProductMapper;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.PurchaseService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.validator.PurchaseProductValidator;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProductsView;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerPurchaseProducts {

    private final PurchaseProductsView productManagement;
    private final InventoryView inventory;
    private final Subject subject;

    private final PurchaseService purchaseService;

    public ControllerPurchaseProducts(ControllerPurchaseProductsBuilder builder) {
        this.productManagement = builder.getProductManagement();
        this.inventory = builder.getInventory();
        this.subject = builder.getSubject();

        this.purchaseService = builder.getPurchaseService();

        connectEvents();
    }

    private void connectEvents() {
        productManagement.setControllerRegister(e -> registerPurchase());
        productManagement.setControllerAddProduct(e -> addProduct());
        productManagement.setControllerSeeInventory(e -> showInventory());
        productManagement.setControllerDeleteOrder(e -> deleteOrder());
        productManagement.setControllerCancelPurchase(e -> clearFields());

        productManagement.setControllerTxt_rSocialFocus(SwingUtils.addFocusListener(productManagement.getTxt_rSocial(), "R. Social"));
        productManagement.setControllerTxt_supplierNitFocus(SwingUtils.addFocusListener(productManagement.getTxt_nit(), "Nit"));
        productManagement.setControllerTxt_phoneFocus(SwingUtils.addFocusListener(productManagement.getTxt_phone(), "Telefono"));
        productManagement.setControllerTxt_addressFocus(SwingUtils.addFocusListener(productManagement.getTxt_address(), "Direccion"));
        productManagement.setControllerTxt_emailFocus(SwingUtils.addFocusListener(productManagement.getTxt_email(), "Correo"));
    }

    private void registerPurchase() {
        try {
            String nit = productManagement.getTxt_nit().getText().trim();
            String rSocial = productManagement.getTxt_rSocial().getText().trim();
            String address = productManagement.getTxt_address().getText().trim();
            String phone = productManagement.getTxt_phone().getText().trim();
            String email = productManagement.getTxt_email().getText().trim();

            DTOProvider dtoProvider = PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email);
            List<DTOProductCreate> listProducts = getProductsList();
            BigDecimal totalPaid = ParserUtils.parseBigDecimal(productManagement.getJlb_totalPurchases().getText().trim(),
                    "cantidad pagada");

            prepareUI();
            doInBackground(dtoProvider, listProducts, totalPaid);
        } catch (InvalidInputException e) {
            MessageUtils.errorMessage(MessageUtils.userMessage(e), productManagement);
        }
    }

    public List<DTOProductCreate> getProductsList() throws InvalidInputException {
        List<DTOProductCreate> list = new ArrayList<>();

        for (int i = 0; i < productManagement.getProductList().getRowCount(); i++) {
            String product = productManagement.getProductList().getValueAt(i, 0).toString();
            int amount = ParserUtils.parseInteger(productManagement.getProductList().getValueAt(i, 1).toString(), "cantidad");
            BigDecimal purchasePrice = ParserUtils.parseBigDecimal(productManagement.getProductList().getValueAt(i, 2).toString(),
                    "precio de compra");
            BigDecimal salePrice = ParserUtils.parseBigDecimal(productManagement.getProductList().getValueAt(i, 3).toString(),
                    "precio de venta");

            list.add(PurchaseProductMapper.toDTOProductCreate(product, amount, purchasePrice, salePrice));
        }

        PurchaseProductValidator.productListEmptyValidation(list);
        return list;
    }

    private void prepareUI() {
        productManagement.getBtn_registerSale().setEnabled(false);
        productManagement.getBtn_registerSale().setText("Registrando compra...");
        productManagement.getBtn_addProduct().setEnabled(false);
    }

    private void doInBackground(DTOProvider dtoProvider, List<DTOProductCreate> listProducts, BigDecimal totalPaid) {
        BackgroundExecutor.execute(() -> {
            try {
                purchaseService.registerPurchase(dtoProvider, totalPaid, listProducts);

                SwingUtilities.invokeLater(() -> {
                    MessageUtils.informationMessage("Compra registrada correctamente.", productManagement);
                    subject.notifyObservers();
                    clearFields();
                });
            } catch (DAOException | SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("La operación fue interrumpida. Intente registrar la compra nuevamente.",
                            productManagement);
                    resetUI();
                });
            }
        });
    }

    private void clearFields() {
        resetUI();
        cleanFieldsProducts();

        productManagement.getTxt_nit().setText("Nit");
        productManagement.getTxt_rSocial().setText("R. Social");
        productManagement.getTxt_address().setText("Direccion");
        productManagement.getTxt_phone().setText("Telefono");
        productManagement.getTxt_email().setText("Correo");
        productManagement.getJlb_totalPurchases().setText("0.00");

        productManagement.getProductList().setRowCount(0);
    }

    private void resetUI() {
        productManagement.getBtn_registerSale().setEnabled(true);
        productManagement.getBtn_registerSale().setText("Pagar");
        productManagement.getBtn_addProduct().setEnabled(true);
    }

    private void addProduct() {
        try {
            String product = productManagement.getTxt_product().getText().trim();
            String amount = productManagement.getTxt_amount().getText().trim();
            String purchasePrice = productManagement.getTxt_purchasePrice().getText().trim();
            String salePrice = productManagement.getTxt_salePrice().getText().trim();

            PurchaseProductValidator.addProductValidation(product, amount, purchasePrice, salePrice);

            BigDecimal purchasePriceValue = ParserUtils.parseBigDecimal(purchasePrice, "precio de compra");
            BigDecimal salePriceValue = ParserUtils.parseBigDecimal(salePrice, "precio de venta");

            PurchaseProductValidator.priceValueValidation(purchasePriceValue, salePriceValue);

            BigDecimal totalPaid = purchasePriceValue.multiply(new BigDecimal(amount));

            Object[] data = new Object[]{product, amount, purchasePrice, salePrice, totalPaid};
            productManagement.getProductList().addRow(data);
            productManagement.getJlb_totalPurchases()
                    .setText(PurchaseProductCalculator.totalAmountPaid(getPurchaseTotals()).toString());
            cleanFieldsProducts();
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), productManagement);
        }
    }

    private void cleanFieldsProducts() {
        productManagement.getTxt_product().setText("");
        productManagement.getTxt_amount().setText("");
        productManagement.getTxt_purchasePrice().setText("");
        productManagement.getTxt_salePrice().setText("");
    }

    private void showInventory() {
        NavigatorUtils.showPanels(ContentView.getJpl_content(), inventory);
        inventory.getTxt_search().setText("Buscar Producto");
    }

    private void deleteOrder() {
        try {
            int row = productManagement.getJtbl_products().getSelectedRow();
            PurchaseProductValidator.rowSelectedValidation(row);

            productManagement.getProductList().removeRow(row);
            productManagement.getJlb_totalPurchases()
                    .setText(PurchaseProductCalculator.totalAmountPaid(getPurchaseTotals()).toString());
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), productManagement);
        }
    }

    private List<BigDecimal> getPurchaseTotals() throws InvalidInputException {
        List<BigDecimal> totalPaidList = new ArrayList<>();

        for (int i = 0; i < productManagement.getJtbl_products().getRowCount(); i++) {
            totalPaidList.add(ParserUtils.parseBigDecimal(productManagement.getJtbl_products()
                    .getValueAt(i, 4).toString(), "total pagado"));
        }

        return totalPaidList;
    }

}
