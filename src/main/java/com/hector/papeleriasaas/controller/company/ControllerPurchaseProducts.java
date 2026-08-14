package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.business.PurchaseProductCalculator;
import com.hector.papeleriasaas.controller.builder.ControllerPurchaseProductsBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.mapper.PurchaseProductMapper;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.provider.DTOProviderDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.ProviderProductsService;
import com.hector.papeleriasaas.service.interfaces.ProvidersService;
import com.hector.papeleriasaas.service.interfaces.PurchaseService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.validator.PurchaseProductValidator;
import com.hector.papeleriasaas.view.content.panels.companies.CompaniesMenu;
import com.hector.papeleriasaas.view.content.panels.companies.InventoryView;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProductsView;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerPurchaseProducts implements Observer {

    private final PurchaseProductsView productManagement;
    private final InventoryView inventory;
    private final Subject subject;

    private final ProductService productService;
    private final PurchaseService purchaseService;
    private final ProviderProductsService providerProductsService;
    private boolean loadingProducts;

    public ControllerPurchaseProducts(ControllerPurchaseProductsBuilder builder) {
        this.productManagement = builder.getProductManagement();
        this.inventory = builder.getInventory();
        this.subject = builder.getSubject();

        this.productService = builder.getProductService();
        this.purchaseService = builder.getPurchaseService();
        this.providerProductsService = builder.getProviderProductsService();

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        productManagement.setControllerRegister(e -> registerPurchase());
        productManagement.setControllerAddProduct(e -> addProduct());
        productManagement.setControllerSeeInventory(e -> showInventory());
        productManagement.setControllerDeleteOrder(e -> deleteOrder());
        productManagement.setControllerCancelPurchase(e -> clearFields());
        productManagement.setControllerSelectProduct(e -> selectProduct());

        productManagement.setControllerTxt_rSocialFocus(SwingUtils.addFocusListener(productManagement.getTxt_rSocial(), "R. Social"));
        productManagement.setControllerTxt_supplierNitFocus(SwingUtils.addFocusListener(productManagement.getTxt_nit(), "Nit"));
        productManagement.setControllerTxt_phoneFocus(SwingUtils.addFocusListener(productManagement.getTxt_phone(), "Telefono"));
        productManagement.setControllerTxt_addressFocus(SwingUtils.addFocusListener(productManagement.getTxt_address(), "Direccion"));
        productManagement.setControllerTxt_emailFocus(SwingUtils.addFocusListener(productManagement.getTxt_email(), "Correo"));
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOProductDetails> productsList = productService.findByAll();

                SwingUtilities.invokeLater(() -> loadData(productsList));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage(e.getMessage(), productManagement));
            }
        });
    }

    private void loadData(List<DTOProductDetails> productLists) {
        loadingProducts = true;

        try {
            productManagement.getCbx_productList().removeAllItems();
            productManagement.getCbx_productList().addItem("Seleccione un producto");
            productLists.forEach(productManagement::loadProductList);
        } finally {
            loadingProducts = false;
        }
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
        cleanFieldsProvider();

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

    private void showInventory() {
        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), inventory);
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

    private void selectProduct() {
        try {
            if (loadingProducts) {
                return;
            }

            String product = (String) productManagement.getCbx_productList().getSelectedItem();

            if (product == null || product.equalsIgnoreCase("Seleccione un producto")) {
                cleanFieldsProducts();
                return;
            }

            Optional<DTOProductDetails> dtoProductDetailsOptional = productService.getProductList().stream()
                    .filter(p -> p.getDescription().equalsIgnoreCase(product))
                    .findFirst();

            if (dtoProductDetailsOptional.isPresent()) {
                DTOProductDetails dtoProductDetails = dtoProductDetailsOptional.get();
                productManagement.loadProduct(dtoProductDetails);

                BackgroundExecutor.execute(() -> {
                    try {
                        Optional<DTOProviderDetails> dtoProviderDetails = providerProductsService.findProviderProducts(dtoProductDetails.getId());

                        if (dtoProviderDetails.isEmpty()) {
                            throw new DataNotFoundException("No se ha encontrado el proveedor");
                        }

                        SwingUtilities.invokeLater(() -> productManagement.loadProvider(dtoProviderDetails.get()));
                    } catch (DAOException e) {
                        cleanFieldsProvider();
                        SwingUtilities.invokeLater(() -> MessageUtils.errorMessage(e.getMessage(), productManagement));
                    }
                });
            } else {
                throw new DataNotFoundException("No se ha encontrado el producto. Lamentamos los inconvenientes");
            }
        } catch (DAOException e) {
            MessageUtils.warningMessage(e.getMessage(), productManagement);
        }
    }

    private void cleanFieldsProducts() {
        productManagement.getTxt_product().setText("");
        productManagement.getTxt_amount().setText("");
        productManagement.getTxt_purchasePrice().setText("");
        productManagement.getTxt_salePrice().setText("");
        productManagement.getCbx_productList().setSelectedIndex(0);
    }

    private void cleanFieldsProvider() {
        productManagement.getTxt_nit().setText("Nit");
        productManagement.getTxt_rSocial().setText("R. Social");
        productManagement.getTxt_address().setText("Direccion");
        productManagement.getTxt_phone().setText("Telefono");
        productManagement.getTxt_email().setText("Correo");
    }

}
