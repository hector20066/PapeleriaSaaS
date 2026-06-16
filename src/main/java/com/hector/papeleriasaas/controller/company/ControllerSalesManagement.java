package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerSalesManagementBuilder;
import com.hector.papeleriasaas.controller.dto.OrderItemSaleDTO;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.model.dto.DTOPurchaseDetailsCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.service.SaleService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;
import com.hector.papeleriasaas.view.content.panels.companies.SalesManagement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesManagement implements Observer, ProductPanelListener {

    private final SalesManagement salesManagement;
    private final Subject subject;

    private final ProductsFacade daoProducts;
    private final SaleService saleService;

    private final List<OrderItemSaleDTO> orderList;

    public ControllerSalesManagement(ControllerSalesManagementBuilder builder) {
        this.salesManagement = builder.getSalesManagement();
        this.subject = builder.getSubject();

        this.daoProducts = builder.getDaoProducts();
        this.saleService = builder.getSaleService();

        this.orderList = new ArrayList<>();

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        salesManagement.setControllerSale(e -> registerSale());
        salesManagement.setControllerSearch(e -> search());
        salesManagement.setControllerCancelSale(e -> cancelSale());

        salesManagement.setControllerTxt_searchFocus(SwingUtils.addFocusListener(salesManagement.getTxt_search(),
                "Buscar Producto"));
        salesManagement.setControllerTxt_customerIdFocus(SwingUtils.addFocusListener(salesManagement.getTxt_customerId(),
                "Cedula"));
        salesManagement.setControllerTxt_customerNameFocus(SwingUtils.addFocusListener(salesManagement.getTxt_customerName(),
                "Nombre"));
        salesManagement.setControllerTxt_customerPhoneFocus(SwingUtils.addFocusListener(salesManagement.getTxt_customerPhone(),
                "Telefono"));
        salesManagement.setControllerTxt_amountPaidFocus(SwingUtils.addFocusListener(salesManagement.getTxt_amountPaid(), "0.00"));
        salesManagement.setControllerTxt_searchDocument(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                filterProducts();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                filterProducts();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                filterProducts();
            }
        });
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOProductDetails> dto = daoProducts.findByAll();
                SwingUtilities.invokeLater(() -> loadData(dto));
            } catch (DAOException e) {
                /*SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar los productos. "
                        + MessageUtils.userMessage(e), salesManagement));*/
            }
        });
    }

    private void loadData(List<DTOProductDetails> list) {
        salesManagement.clearPanel();

        list.stream()
                .filter(Objects::nonNull)
                .forEach(dto -> salesManagement.addProductPanel(dto, this));
    }

    @Override
    public void onIncreaseQuantity(DTOProductDetails product, JLabel quantityLabel) {
        int quantity = Integer.parseInt(quantityLabel.getText());

        if ((quantity + 1) > product.getAmount()) {
            MessageUtils.warningMessage("No hay unidades suficientes de " + product.getDescription()
                    + ". Disponible: " + product.getAmount(), salesManagement);
            return;
        }

        quantityLabel.setText(String.valueOf(quantity + 1));
    }

    @Override
    public void onDecreaseQuantity(DTOProductDetails product, JLabel quantityLabel) {
        int current = Integer.parseInt(quantityLabel.getText());

        if (current > 0) {
            quantityLabel.setText(String.valueOf(current - 1));
        }

        if ((current - 1) == 0) {
            salesManagement.getOrderItemList().removeItem(product.getDescription());
            this.orderList.removeIf(dto -> dto.getProductName().equalsIgnoreCase(product.getDescription()));
            updateTotalSale();
        }
    }

    @Override
    public void onAddToOrder(DTOProductDetails product, int quantity) {
        if (quantity == 0) {
            return;
        }

        OrderItemSaleDTO orderDto = new OrderItemSaleDTO(product.getDescription(), product.getId(), quantity,
                product.getSalePrice());

        if (this.orderList.contains(orderDto)) {
            int index = this.orderList.indexOf(orderDto);
            this.orderList.set(index, orderDto);
            salesManagement.getOrderItemList().updateItem(orderDto.getProductName(), quantity, product.getSalePrice());
            updateTotalSale();
            return;
        }

        this.orderList.add(orderDto);
        salesManagement.getOrderItemList().addItem(orderDto.getProductName(), quantity, product.getSalePrice());
        updateTotalSale();
    }

    private BigDecimal getTotalSale() {
        return this.orderList.stream()
                .map(dto -> dto.getUnitPrice().multiply(new BigDecimal(dto.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void updateTotalSale() {
        salesManagement.getJlb_totalSale().setText(String.valueOf(getTotalSale()));
    }

    private void registerSale() {
        try {
            String customerName = salesManagement.getTxt_customerName().getText().trim();
            String customerPhone = salesManagement.getTxt_customerPhone().getText().trim();
            String customerId = salesManagement.getTxt_customerId().getText().trim();
            String totalSale = salesManagement.getJlb_totalSale().getText().trim();
            String amountPaid = salesManagement.getTxt_amountPaid().getText().trim();
            List<DTOPurchaseDetailsCreate> list = listProductDetails();

            if (ValidationUtils.isEmptyFields(amountPaid)) {
                throw new EmptyFieldException("valor pagado");
            }

            BigDecimal amountPaidValue = ParserUtils.parseBigDecimal(amountPaid, "valor pagado");
            BigDecimal totalSaleValue = ParserUtils.parseBigDecimal(totalSale, "total de la venta");

            if (amountPaidValue.signum() < 0) {
                throw new InvalidInputException("El valor pagado no puede ser negativo.");
            }

            if (!(amountPaidValue.compareTo(totalSaleValue) >= 0)) {
                validationsDebts(customerId, customerName);
            } else {
                if (ValidationUtils.isInvalidInput(customerId, "Cedula")) {
                    customerId = null;
                }

                if (ValidationUtils.isInvalidInput(customerName, "Nombre")) {
                    customerName = null;
                }

                if (ValidationUtils.isInvalidInput(customerPhone, "Telefono")) {
                    customerPhone = null;
                }
            }

            prepareUI();

            Integer customerIdInt = null;

            if (customerId != null) {
                customerIdInt = ParserUtils.parseInteger(customerId, "id cliente");
            }
            
            doInBackground(customerIdInt, customerName, customerPhone, amountPaidValue, totalSaleValue, list);
        } catch (InvalidInputException e) {
            MessageUtils.errorMessage(MessageUtils.userMessage(e), salesManagement);
        }
    }

    private List<DTOPurchaseDetailsCreate> listProductDetails() throws InvalidInputException {
        List<DTOPurchaseDetailsCreate> list = this.orderList.stream()
                .map(orderDto -> {
                    DTOPurchaseDetailsCreate dto = new DTOPurchaseDetailsCreate();
                    dto.setProductId(orderDto.getProductId());
                    dto.setAmount(orderDto.getAmount());
                    dto.setUnitPrice(orderDto.getUnitPrice());

                    return dto;
                })
                .toList();

        if (list.isEmpty()) {
            throw new InvalidInputException("Agregue al menos un producto al carrito antes de registrar la venta.");
        }

        return list;
    }

    private void validationsDebts(String customerId, String customerName) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(customerId, "Cedula")) {
            throw new EmptyFieldException("cédula del cliente (requerida para ventas con deuda)");
        }

        if (!ValidationUtils.isNumeric(customerId)) {
            throw new InvalidNumberInputException("cédula del cliente");
        }

        if (ValidationUtils.isInvalidInput(customerName, "Nombre")) {
            throw new EmptyFieldException("nombre del cliente (requerido para ventas con deuda)");
        }
    }

    private void prepareUI() {
        salesManagement.getBtn_sale().setEnabled(false);
        salesManagement.getBtn_sale().setText("Registrando venta...");
    }

    private void doInBackground(Integer customerId, String customerName, String customerPhone, BigDecimal amountPaid,
                                BigDecimal totalSale, List<DTOPurchaseDetailsCreate> list) {
        BackgroundExecutor.execute(() -> {
            try {
                int invoiceNumber = saleService.registerSale(customerId, customerName, customerPhone, amountPaid, totalSale, list);
                String lowStock = checkLowStock();

                SwingUtilities.invokeLater(() -> generateInvoice(invoiceNumber, customerName, customerId, totalSale, lowStock, amountPaid));
            } catch (DAOException | SQLException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage(MessageUtils.userMessage(e), salesManagement);
                    resetUI();
                });
            }
        });
    }

    private String checkLowStock() throws DAOException {
        StringBuilder productsOutOfStock = new StringBuilder();
        List<DTOProductDetails> products = daoProducts.findByAll();

        products.stream()
                .filter(product -> product.getAmount() < 5)
                .map(DTOProductDetails::getDescription)
                .distinct()
                .forEach(p -> productsOutOfStock.append(p).append(", "));

        if (!productsOutOfStock.isEmpty()) {
            productsOutOfStock.setLength(productsOutOfStock.length() - 2);
        }

        return productsOutOfStock.toString();
    }

    private void generateInvoice(int invoiceNumber, String customerName, Integer customerId, BigDecimal totalSale,
                                 String lowStock, BigDecimal amountPaid) {
        ReportExecutor.execute(() -> {
            try {
                String company = "Papeleria la Esquina";
                String nit = "NIT: 12345678";
                String address = "Direccion: TR 24 16-25";
                String phone = "Telefono: 3137762626";
                List<OrderItemSaleDTO> details = this.orderList;

                String compileReport = "/reports/Factura80mm.jrxml";
                Map<String, Object> params = new HashMap<>();
                params.put("company", company);
                params.put("nit", nit);
                params.put("address", address);
                params.put("phone", phone);
                params.put("invoiceNumber", invoiceNumber);
                params.put("date", LocalDate.now());
                params.put("customer", customerName);
                params.put("customerId", customerId);
                params.put("iva", new BigDecimal(0));
                params.put("subTotal", totalSale);
                params.put("total", totalSale);

                JasperPrint report = ReportService.generate(details, compileReport, params);
                AtomicBoolean executed = new AtomicBoolean(false);

                SwingUtilities.invokeLater(() -> {
                    JasperViewer viewer = new JasperViewer(report, false);
                    viewer.setLocationRelativeTo(null);
                    viewer.setResizable(false);

                    viewer.addWindowListener(new WindowAdapter() {
                        private void finishSale() {
                            if (executed.compareAndSet(false, true)) {
                                handleSaleResult(lowStock, amountPaid, totalSale, customerName);
                            }
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                            finishSale();
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                            finishSale();
                        }
                    });

                    viewer.setVisible(true);
                });
            } catch (JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar la "
                        + "factura de la venta.", salesManagement));
            }
        });
    }

    private void handleSaleResult(String lowStock, BigDecimal amountPaid, BigDecimal totalSale, String customer) {
        if (!lowStock.isEmpty()) {
            MessageUtils.warningMessage("Revise el inventario. Estos productos tienen menos de 5 unidades:\n"
                    + lowStock, salesManagement);
        }

        if (amountPaid.compareTo(totalSale) < 0) {
            MessageUtils.informationMessage("La venta quedó con saldo pendiente para el cliente: " + customer,
                    salesManagement);
        }

        subject.notifyObservers();
        clearFields();
    }

    private void resetUI() {
        salesManagement.getBtn_sale().setEnabled(true);
        salesManagement.getBtn_sale().setText("Pagar");
    }

    private void search() {
        List<DTOProductDetails> listProducts = daoProducts.getProductList();
        String searchParameter = salesManagement.getTxt_search().getText().trim();

        Optional<DTOProductDetails> product = listProducts.stream()
                .filter(dto -> dto.getDescription().equalsIgnoreCase(searchParameter))
                .findFirst();

        if (product.isPresent()) {
            salesManagement.clearPanel();
            salesManagement.addProductPanel(product.get(), this);
        }
    }

    private void filterProducts() {
        List<DTOProductDetails> listProducts = daoProducts.getProductList();
        String searchParameter = salesManagement.getTxt_search().getText().trim();
        salesManagement.getJpl_content().removeAll();

        if (searchParameter.equalsIgnoreCase("Buscar Producto") || searchParameter.isBlank()) {
            listProducts.forEach(dto -> salesManagement.addProductPanel(dto, this));
        } else {
            listProducts.stream()
                    .filter(dto -> dto.getDescription().toLowerCase().contains(searchParameter.toLowerCase()))
                    .forEach(dto -> salesManagement.addProductPanel(dto, this));
        }

        salesManagement.getJpl_content().revalidate();
        salesManagement.getJpl_content().repaint();
    }

    private void cancelSale() {
        clearFields();
        salesManagement.clearPanel();

        BackgroundExecutor.execute(() -> {
            List<DTOProductDetails> list = daoProducts.getProductList();

            SwingUtilities.invokeLater(() -> list.stream()
                        .filter(Objects::nonNull)
                        .forEach(dto -> salesManagement.addProductPanel(dto, this))
            );
        });
    }

    private void clearFields() {
        salesManagement.getOrderItemList().clearItems();
        salesManagement.getJlb_totalSale().setText("0.00");
        salesManagement.getTxt_amountPaid().setText("0.00");
        salesManagement.getTxt_customerName().setText("Nombre");
        salesManagement.getTxt_customerPhone().setText("Telefono");
        salesManagement.getTxt_customerId().setText("Cedula");

        this.orderList.clear();

        resetUI();
    }

}
