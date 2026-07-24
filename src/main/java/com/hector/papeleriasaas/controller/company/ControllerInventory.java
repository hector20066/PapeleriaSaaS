package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerInventoryBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.service.impl.ProductServiceImpl;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.product.DTOProductUpdate;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportServiceImpl;
import com.hector.papeleriasaas.utils.*;
import com.hector.papeleriasaas.validator.InventoryValidator;
import com.hector.papeleriasaas.view.content.panels.companies.Inventory;
import com.hector.papeleriasaas.view.content.panels.companies.ModifyProduct;
import com.hector.papeleriasaas.view.content.panels.companies.PurchaseProducts;
import com.hector.papeleriasaas.view.report.ReportViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerInventory implements Observer {

    private final Inventory inventory;
    private final PurchaseProducts purchaseProducts;
    private final ModifyProduct modifyProduct;
    private final Subject subject;

    private final ProductServiceImpl productsFacade;

    public ControllerInventory(ControllerInventoryBuilder builder) {
        this.inventory = builder.getInventory();
        this.purchaseProducts = builder.getPurchaseProducts();
        this.modifyProduct = builder.getModifyProduct();
        this.subject = builder.getSubject();

        this.productsFacade = builder.getProductService();

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        inventory.setControllerSearch(e -> search());
        inventory.setControllerModify(e -> modify());
        inventory.setControllerExport(e -> export());
        inventory.setControllerDelete(e -> delete());
        inventory.setControllerBack(e -> back());

        inventory.setControllerTxt_searchFocus(SwingUtils.addFocusListener(inventory.getTxt_search(), "Buscar Producto"));
        inventory.setControllerTxt_searchDocument(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtils.filterTable(inventory.getTxt_search(), inventory.getJtbl_inventory(), "Buscar Producto");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtils.filterTable(inventory.getTxt_search(), inventory.getJtbl_inventory(), "Buscar Producto");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtils.filterTable(inventory.getTxt_search(), inventory.getJtbl_inventory(), "Buscar Producto");
            }
        });
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOProductDetails> productList = productsFacade.findByAll();
                SwingUtilities.invokeLater(() -> loadData(productList));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el inventario. "
                        + MessageUtils.userMessage(e), inventory));
            }
        });
    }

    private void loadData(List<DTOProductDetails> productList) {
        inventory.getInventoryList().setRowCount(0);
        productList.forEach(inventory::updatePanel);
    }

    private void search() {
        try {
            String product = inventory.getTxt_search().getText().trim();
            InventoryValidator.searchParamValidation(product);
            doInBackgroundSearch(product);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), inventory);
        }
    }

    private void doInBackgroundSearch(String product) {
        BackgroundExecutor.execute(() -> {
            try {
                Optional<DTOProductDetails> dtoOptional = ValidationUtils.isNumeric(product)
                        ? productsFacade.findById(Integer.parseInt(product))
                        : productsFacade.findByName(product);

                InventoryValidator.dtoProductEmptyValidation(dtoOptional, product);

                SwingUtilities.invokeLater(() -> {
                    inventory.getInventoryList().setRowCount(0);
                    inventory.updatePanel(dtoOptional.get());
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar el producto. " + MessageUtils.userMessage(e),
                            inventory);
                    inventory.getTxt_search().setText("Buscar Producto");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void modify() {
        try {
            int row = inventory.getJtbl_inventory().getSelectedRow();
            InventoryValidator.rowValidation(row);

            int id = (int) inventory.getJtbl_inventory().getValueAt(row, 0);
            String product = String.valueOf(inventory.getJtbl_inventory().getValueAt(row, 1));
            int amount = (int) inventory.getJtbl_inventory().getValueAt(row, 2);
            BigDecimal purchasePrice = ParserUtils.parseBigDecimal(inventory.getJtbl_inventory()
                    .getValueAt(row, 3).toString(), "precio de compra");
            BigDecimal salePrice = ParserUtils.parseBigDecimal(inventory.getJtbl_inventory()
                    .getValueAt(row, 4).toString(), "precio de venta");

            modifyProduct.loadDataModify(new DTOProductUpdate.Builder()
                    .id(id)
                    .description(product)
                    .amount(amount)
                    .purchasePrice(purchasePrice)
                    .salePrice(salePrice)
                    .build());

            NavigatorUtils.showPanels(modifyProduct);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), inventory);
        }
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTOProductDetails> productList = productsFacade.findByAll();
                String compileReport = "/reports/Inventario.jrxml";
                Map<String, Object> params = new HashMap<>();

                JasperPrint report = ReportServiceImpl.generate(productList, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", inventory));
            }
        });
    }

    private void delete() {
        try {
            int selectedRow = inventory.getJtbl_inventory().getSelectedRow();
            InventoryValidator.rowValidation(selectedRow);

            int productId = (int) inventory.getJtbl_inventory().getValueAt(selectedRow, 0);
            doInBackground(productId);
        } catch (InvalidInputException e) {
            MessageUtils.errorMessage(e.getMessage(), inventory);
        }
    }

    private void doInBackground(int productId) {
        BackgroundExecutor.execute(() -> {
            try {
                productsFacade.delete(productId);

                SwingUtilities.invokeLater(() -> {
                    MessageUtils.informationMessage("Producto eliminado correctamente.", inventory);
                    subject.notifyObservers();
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible eliminar el producto. " + MessageUtils.userMessage(e),
                            inventory);
                });
            }
        });
    }

    private void back() {
        NavigatorUtils.showPanels(purchaseProducts);
    }

}
