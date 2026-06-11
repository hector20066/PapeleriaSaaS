package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.facade.interfaces.SupplierFacade;
import com.hector.papeleriasaas.model.dto.DTOSupplier;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;
import com.hector.papeleriasaas.view.content.panels.Suppliers;
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

    private final SupplierFacade daoSupplier;

    public ControllerSuppliers(Suppliers suppliers, Subject subject, SupplierFacade daoSupplier) {
        this.suppliers = suppliers;
        this.subject = subject;

        this.daoSupplier = daoSupplier;
        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        suppliers.setControllerSearch(e -> search());
        suppliers.setControllerExport(e -> export());

        suppliers.setControllerTxt_searchFocusListener(SwingUtils.addFocusListener(suppliers.getTxt_search(), "Buscar proveedor"));
        suppliers.setControllerTxt_searchDocumentListener(new DocumentListener() {
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
        });
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOSupplier> suppliersList = daoSupplier.findByAll();
                SwingUtilities.invokeLater(() -> loadData(suppliersList));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar los proveedores. "
                        + MessageUtils.userMessage(e), suppliers));
            }
        });
    }

    private void loadData(List<DTOSupplier> suppliersList) {
        suppliers.getModel().setRowCount(0);
        suppliersList.forEach(suppliers::updatePanel);
    }

    private void search() {
        String param = suppliers.getTxt_search().getText().trim();

        if (ValidationUtils.isInvalidInput(param, "Buscar proveedor")) {
            MessageUtils.warningMessage("Por favor ingrese un término de búsqueda válido.", suppliers);
            return;
        }
        
        doInBackgroundSearch(param);
    }

    private void doInBackgroundSearch(String param) {
        BackgroundExecutor.execute(() -> {
            try {
                Optional<DTOSupplier> supplier = ValidationUtils.isNumeric(param)
                        ? daoSupplier.findById(Integer.parseInt(param))
                        : daoSupplier.findByName(param);

                if (supplier.isEmpty()) {
                    throw new InvalidInputException("No se encontró ningún proveedor con ese criterio de búsqueda.");
                }

                SwingUtilities.invokeLater(() -> {
                    suppliers.getModel().setRowCount(0);
                    suppliers.updatePanel(supplier.get());
                });
            } catch (InvalidInputException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.warningMessage(MessageUtils.userMessage(e), suppliers);
                    suppliers.getTxt_search().setText("Buscar proveedor");
                    subject.notifyObservers();
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
                List<DTOSupplier> suppliersList = daoSupplier.findByAll();
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

}
