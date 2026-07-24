package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.service.ProvidersService;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;
import com.hector.papeleriasaas.validator.ProvidersValidator;
import com.hector.papeleriasaas.view.content.panels.companies.Suppliers;
import com.hector.papeleriasaas.view.report.ReportViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSuppliers implements Observer {

    private final Suppliers suppliers;
    private final Subject subject;

    private final ProvidersService providersService;

    public ControllerSuppliers(Suppliers suppliers, Subject subject, ProvidersService providersService) {
        this.suppliers = suppliers;
        this.subject = subject;

        this.providersService = providersService;
        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        suppliers.setControllerSearch(e -> search());
        suppliers.setControllerExport(e -> export());

        suppliers.setControllerTxt_searchFocusListener(SwingUtils.addFocusListener(suppliers.getTxt_search(), "Buscar proveedor"));
        suppliers.setControllerTxt_searchDocumentListener(documentListenerEvent());
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOProvider> suppliersList = providersService.findByAll();
                SwingUtilities.invokeLater(() -> loadData(suppliersList));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar los proveedores. "
                        + MessageUtils.userMessage(e), suppliers));
            }
        });
    }

    private void loadData(List<DTOProvider> suppliersList) {
        suppliers.getModel().setRowCount(0);
        suppliersList.forEach(suppliers::updatePanel);
    }

    private void search() {
        try {
            String param = suppliers.getTxt_search().getText().trim();
            ProvidersValidator.searchParamValidation(param);
            doInBackgroundSearch(param);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), suppliers);
        }
    }

    private void doInBackgroundSearch(String param) {
        BackgroundExecutor.execute(() -> {
            try {
                Optional<DTOProvider> supplier = ValidationUtils.isNumeric(param)
                        ? providersService.findById(Integer.parseInt(param))
                        : providersService.findByName(param);

                ProvidersValidator.providerEmptyValidation(supplier, param);

                SwingUtilities.invokeLater(() -> {
                    suppliers.getModel().setRowCount(0);
                    suppliers.updatePanel(supplier.get());
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar el proveedor. " + MessageUtils.userMessage(e),
                            suppliers);
                    suppliers.getTxt_search().setText("Buscar proveedor");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTOProvider> suppliersList = providersService.findByAll();
                String compileReport = "/reports/Proveedores.jrxml";
                Map<String, Object> params = new HashMap<>();

                JasperPrint report = ReportService.generate(suppliersList, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", suppliers));
            }
        });
    }

    private DocumentListener documentListenerEvent() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(suppliers.getTxt_search(), suppliers.getJtbl_suppliers(), "Buscar proveedor");
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(suppliers.getTxt_search(), suppliers.getJtbl_suppliers(), "Buscar proveedor");
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(suppliers.getTxt_search(), suppliers.getJtbl_suppliers(), "Buscar proveedor");
            }
        };
    }

}
