package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.*;
import com.hector.papeleriasaas.service.CustomerService;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.observer.*;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.utils.*;
import com.hector.papeleriasaas.validator.CustomerValidator;
import com.hector.papeleriasaas.view.content.panels.companies.Customers;
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
public class ControllerCustomer implements Observer {

    private final Customers customers;
    private final Subject subject;

    private final CustomerService customerService;

    public ControllerCustomer(Customers customers, Subject subject, CustomerService customerService) {
        this.customers = customers;
        this.subject = subject;

        this.customerService = customerService;
        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        customers.setControllerSearch(e -> search());
        customers.setControllerExport(e -> export());

        customers.setControllerTxt_searchFocusListener(SwingUtils.addFocusListener(customers.getTxt_search(), "Buscar Cliente"));
        customers.setControllerTxt_searchDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(customers.getTxt_search(), customers.getJtbl_customers(), "Buscar Cliente");
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(customers.getTxt_search(), customers.getJtbl_customers(), "Buscar Cliente");
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                SwingUtils.filterTable(customers.getTxt_search(), customers.getJtbl_customers(), "Buscar Cliente");
            }
        });
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOCustomerDetails> customersList = customerService.findByAll();
                int totalRecords = customerService.countRecords();

                SwingUtilities.invokeLater(() -> loadData(customersList, totalRecords));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar los clientes. "
                        + MessageUtils.userMessage(e), customers));
            }
        });
    }

    private void loadData(List<DTOCustomerDetails> customersList, int totalRecords) {
        customers.getModel().setRowCount(0);
        customers.getTxt_totalRecords().setText(String.valueOf(totalRecords));

        customersList.forEach(customers::updatePanel);
    }

    private void search() {
        try {
            String customer = customers.getTxt_search().getText().trim();
            CustomerValidator.searchParamValidation(customer);
            doInBackground(customer);
        } catch (InvalidInputException e) {
            MessageUtils.errorMessage(e.getMessage(), customers);
        }
    }

    private void doInBackground(String customer) {
        BackgroundExecutor.execute(() -> {
            try {
                Optional<DTOCustomerDetails> dtoOptional = ValidationUtils.isNumeric(customer)
                        ? customerService.findById(ParserUtils.parseInteger(customer, "cedula"))
                        : customerService.findByName(customer);

                CustomerValidator.optionalEmptyValidation(dtoOptional, customer);

                SwingUtilities.invokeLater(() -> {
                    customers.getModel().setRowCount(0);
                    customers.updatePanel(dtoOptional.get());
                });
            } catch (InvalidInputException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.warningMessage(MessageUtils.userMessage(e), customers);
                    customers.getTxt_search().setText("Buscar Cliente");
                    subject.notifyObservers();
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar el cliente. " + MessageUtils.userMessage(e),
                            customers);
                    customers.getTxt_search().setText("Buscar Cliente");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTOCustomerDetails> customersList = customerService.findByAll();
                String compileReport = "/reports/Clientes.jrxml";
                Map<String, Object> params = new HashMap<>();

                JasperPrint report = ReportService.generate(customersList, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", customers));
            }
        });
    }

}
