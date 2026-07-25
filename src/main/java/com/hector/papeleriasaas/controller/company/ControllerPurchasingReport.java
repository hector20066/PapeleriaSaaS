package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportServiceImpl;
import com.hector.papeleriasaas.service.interfaces.PurchaseService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.validator.PurchasingReportValidator;
import com.hector.papeleriasaas.view.content.panels.companies.PurchasingReport;
import com.hector.papeleriasaas.view.report.ReportViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerPurchasingReport implements Observer {

    private final PurchasingReport purchasingReport;
    private final Subject subject;

    private final PurchaseService purchaseService;

    public ControllerPurchasingReport(PurchasingReport purchasingReport, Subject subject, PurchaseService purchaseService) {
        this.purchasingReport = purchasingReport;
        this.subject = subject;
        this.purchaseService = purchaseService;

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        purchasingReport.setControllerSearch(e -> search());
        purchasingReport.setControllerExport(e -> export());

        purchasingReport.setControllerTxt_searchFocus(SwingUtils.addFocusListener(purchasingReport.getTxt_search(),
                "Buscar Compra"));
        purchasingReport.setControllerTxt_searchDocument(documentListenerEvents());
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOPurchaseDetails> list = purchaseService.findByAll();
                SwingUtilities.invokeLater(() -> loadData(list));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el reporte de compras. "
                        + MessageUtils.userMessage(e), purchasingReport));
            }
        });
    }

    private void loadData(List<DTOPurchaseDetails> list) {
        purchasingReport.getPurchaseList().setRowCount(0);
        list.forEach(purchasingReport::updatePanel);
    }

    private void search() {
        try {
            String param = purchasingReport.getTxt_search().getText().trim();
            PurchasingReportValidator.searchParamValidator(param);
            doInBackgroundSearch(param);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), purchasingReport);
            purchasingReport.getTxt_search().setText("Buscar Compra");
            subject.notifyObservers();
        }
    }

    private void doInBackgroundSearch(String param) {
        BackgroundExecutor.execute(() -> {
            try {
                List<DTOPurchaseDetails> list = purchaseService.findById(Integer.parseInt(param));

                SwingUtilities.invokeLater(() -> {
                    purchasingReport.getPurchaseList().setRowCount(0);
                    list.forEach(purchasingReport::updatePanel);
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar la compra. " + MessageUtils.userMessage(e),
                            purchasingReport);
                    purchasingReport.getTxt_search().setText("Buscar Compra");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTOPurchaseDetails> purchasesList = purchaseService.findByAll();
                String compileReport = "/reports/ReporteCompras.jrxml";
                Map<String, Object> params = new HashMap<>();

                JasperPrint report = ReportServiceImpl.generate(purchasesList, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", purchasingReport));
            }
        });
    }

    private DocumentListener documentListenerEvents() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtils.filterTable(purchasingReport.getTxt_search(), purchasingReport.getJtbl_purchases(), "Buscar Compra");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtils.filterTable(purchasingReport.getTxt_search(), purchasingReport.getJtbl_purchases(), "Buscar Compra");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtils.filterTable(purchasingReport.getTxt_search(), purchasingReport.getJtbl_purchases(), "Buscar Compra");
            }
        };
    }

}
