package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerDebtReportBuilder;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.facade.interfaces.DebtsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.model.dto.DTODebtDetails;
import com.hector.papeleriasaas.model.enums.PaymentStatus;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.validator.DebtReportValidator;
import com.hector.papeleriasaas.view.content.panels.companies.DebtReport;
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

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDebtReport implements Observer {

    private final DebtReport debtReport;
    private final Subject subject;

    private final DebtsFacade daoDebts;
    private final SalesFacade daoSales;

    public ControllerDebtReport(ControllerDebtReportBuilder builder) {
        this.debtReport = builder.getDebtReport();
        this.subject = builder.getSubject();

        this.daoDebts = builder.getDaoDebts();
        this.daoSales = builder.getDaoSales();

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        debtReport.setControllerPay(e -> payDebt());
        debtReport.setControllerSearch(e -> search());
        debtReport.setControllerCbo_status(e -> filterDebts());
        debtReport.setControllerExport(e -> export());

        debtReport.setControllerTxt_searchCustomerFocus(SwingUtils.addFocusListener(debtReport.getTxt_searchCustomer(),
                "Buscar Cliente"));
        debtReport.setControllerTxt_searchCustomerDocument(documentListenerEvent());
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTODebtDetails> list = daoDebts.findByAll();
                SwingUtilities.invokeLater(() -> loadData(list));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el reporte de deudas. "
                        + MessageUtils.userMessage(e), debtReport));
            }
        });
    }

    private void loadData(List<DTODebtDetails> list) {
        debtReport.getDebtList().setRowCount(0);
        list.forEach(debtReport::updatePanel);
    }

    private void payDebt() {
        try {
            int row = debtReport.getJtbl_debts().getSelectedRow();

            DebtReportValidator.rowSelectedValidation(row);
            DebtReportValidator.compareStatusValidation((PaymentStatus) debtReport.getJtbl_debts().getValueAt(row, 8));

            int numSale = (int) debtReport.getJtbl_debts().getValueAt(row, 3);
            BigDecimal amountPaid = ParserUtils.parseBigDecimal(debtReport.getJtbl_debts().getValueAt(row, 6).toString(),
                    "cantidad pagada");
            BigDecimal debt = ParserUtils.parseBigDecimal(debtReport.getJtbl_debts().getValueAt(row, 7).toString(),
                    "deuda");

            String strPass = MessageUtils.debtPaymentMessage("Deuda actual: " + debt
                    + "\nIngrese el valor que desea abonar.", debtReport);

            if (strPass == null || strPass.isBlank()) {
                return;
            }

            BigDecimal passDecimal = ParserUtils.parseBigDecimal(strPass, "valor del abono");
            DebtReportValidator.passValidation(passDecimal, debt);

            prepareUI();
            doInBackground(numSale, amountPaid.add(passDecimal));
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), debtReport);
        }
    }

    private void prepareUI() {
        debtReport.getBtn_pay().setText("Pagando deuda...");
        debtReport.getBtn_pay().setEnabled(false);
        debtReport.getBtn_export().setEnabled(false);
    }

    private void doInBackground(int numSale, BigDecimal newAmountPaid) {
        BackgroundExecutor.execute(() -> {
            try {
                daoSales.modify(newAmountPaid, numSale);

                SwingUtilities.invokeLater(() -> {
                    MessageUtils.informationMessage("Abono registrado correctamente.", debtReport);
                    resetUI();
                    subject.notifyObservers();
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible registrar el abono. " + MessageUtils.userMessage(e),
                            debtReport);
                    resetUI();
                });
            }
        });
    }

    private void resetUI() {
        debtReport.getBtn_pay().setText("Pagar Deuda");
        debtReport.getBtn_pay().setEnabled(true);
        debtReport.getBtn_export().setEnabled(true);
    }

    private void search() {
        try {
            String customer = debtReport.getTxt_searchCustomer().getText().trim();
            DebtReportValidator.searchParamValidation(customer);
            doInBackgroundSearch(customer);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(e.getMessage(), debtReport);
        }
    }

    private void doInBackgroundSearch(String customer) {
        BackgroundExecutor.execute(() -> {
            try {
                List<DTODebtDetails> list = daoDebts.findByCustomer(customer);
                DebtReportValidator.debtListEmptyValidation(list, customer);

                SwingUtilities.invokeLater(() -> {
                    debtReport.getDebtList().setRowCount(0);
                    list.forEach(debtReport::updatePanel);
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar las deudas del cliente. "
                            + MessageUtils.userMessage(e), debtReport);
                    debtReport.getTxt_search().setText("Buscar Cliente");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void filterDebts() {
        String parameter = (String) debtReport.getCbo_status().getSelectedItem();
        debtReport.getCbo_status().setEnabled(false);

        BackgroundExecutor.execute(() -> {
            try {
                List<DTODebtDetails> listDebts = switch (parameter) {
                    case "Todas" -> daoDebts.findByAll();
                    case "Pendientes" -> daoDebts.findByStatus(PaymentStatus.PENDING);
                    case "Pagadas" -> daoDebts.findByStatus(PaymentStatus.PAID);
                    default -> throw new IllegalStateException("Parámetro inesperado " + parameter);
                };

                SwingUtilities.invokeLater(() -> {
                    debtReport.getDebtList().setRowCount(0);
                    listDebts.forEach(debtReport::updatePanel);
                    debtReport.getCbo_status().setEnabled(true);
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible cargar las deudas. " + MessageUtils.userMessage(e),
                            debtReport);
                    debtReport.getCbo_status().setEnabled(true);
                });
            }
        });
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTODebtDetails> debtDetails = daoDebts.findByAll();
                String compileReport = "/reports/ReporteDeudas.jrxml";
                Map<String, Object> params = new HashMap<>();

                JasperPrint report = ReportService.generate(debtDetails, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", debtReport));
            }
        });
    }

    private DocumentListener documentListenerEvent() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtils.filterTable(debtReport.getTxt_searchCustomer(), debtReport.getJtbl_debts(), "Buscar Cliente");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtils.filterTable(debtReport.getTxt_searchCustomer(), debtReport.getJtbl_debts(), "Buscar Cliente");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtils.filterTable(debtReport.getTxt_searchCustomer(), debtReport.getJtbl_debts(), "Buscar Cliente");
            }
        };
    }

}
