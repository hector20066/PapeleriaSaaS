package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerSalesReportBuilder;
import com.hector.papeleriasaas.controller.dto.DTOChartSales;
import com.hector.papeleriasaas.controller.dto.DTOReportSummary;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.executor.BackgroundExecutor;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.executor.ReportExecutor;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.mapper.DashboardCompanyMapper;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.observer.Subject;
import com.hector.papeleriasaas.service.ReportService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.utils.SwingUtils;
import com.hector.papeleriasaas.validator.SalesReportValidator;
import com.hector.papeleriasaas.view.content.panels.companies.SalesReport;
import com.hector.papeleriasaas.view.report.ReportViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerSalesReport implements Observer {

    private final SalesReport salesReport;
    private final Subject subject;

    private final SalesFacade daoSales;
    private final ProductsFacade daoProducts;

    public ControllerSalesReport(ControllerSalesReportBuilder builder) {
        this.salesReport = builder.getSalesReport();
        this.subject = builder.getSubject();

        this.daoSales = builder.getDaoSales();
        this.daoProducts = builder.getDaoProducts();

        this.subject.register(this);

        connectEvents();
    }

    private void connectEvents() {
        salesReport.setControllerSearch(e -> search());
        salesReport.setControllerExport(e -> export());

        salesReport.setControllerTxt_searchFocus(SwingUtils.addFocusListener(salesReport.getTxt_search(), "Buscar Venta"));
        salesReport.setControllerTxt_search(documentListenerEvent());
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> list = daoSales.findByAll();
                SwingUtilities.invokeLater(() -> loadData(list));
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el reporte de ventas. "
                        + MessageUtils.userMessage(e), salesReport));
            }
        });
    }

    private void loadData(List<DTOSaleDetails> list) {
        salesReport.getSalesList().setRowCount(0);
        list.forEach(salesReport::updatePanel);
    }

    private void search() {
        try {
            String param = salesReport.getTxt_search().getText().trim();
            SalesReportValidator.searchValidation(param);
            doInBackgroundSearch(param);
        } catch (InvalidInputException e) {
            MessageUtils.warningMessage(MessageUtils.userMessage(e), salesReport);
            salesReport.getTxt_search().setText("Buscar Venta");
            subject.notifyObservers();
        }
    }

    private void doInBackgroundSearch(String param) {
        BackgroundExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> list = daoSales.findById(Integer.parseInt(param));
                SalesReportValidator.listEmptyValidation(list, param);

                SwingUtilities.invokeLater(() -> {
                    salesReport.getSalesList().setRowCount(0);
                    list.forEach(salesReport::updatePanel);
                });
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> {
                    MessageUtils.errorMessage("No fue posible buscar la venta. " + MessageUtils.userMessage(e),
                            salesReport);
                    salesReport.getTxt_search().setText("Buscar Venta");
                    subject.notifyObservers();
                });
            }
        });
    }

    private void export() {
        ReportExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> salesList = daoSales.findByAll();
                List<DTOProductDetails> productsList = daoProducts.findByAll();
                Optional<DTOProductBestSelling> bestSelling = daoProducts.bestSelling();

                SalesReportValidator.bestSellingEmptyValidation(bestSelling);

                DTOReportSummary dtoSummary = DashboardCompanyMapper.getReportSummary(productsList, salesList, bestSelling.get());
                List<DTOChartSales> listChartData = DashboardCompanyMapper.getChartSalesData(salesList, LocalDate.now());

                String compileReport = "/reports/ReporteVentas.jrxml";

                Map<String, Object> params = new HashMap<>();
                params.put("totalPurchases", dtoSummary.getTotalPurchases());
                params.put("totalSales", dtoSummary.getTotalSales());
                params.put("totalProfits", dtoSummary.getTotalProfits());
                params.put("productName", dtoSummary.getProductName());
                params.put("amountProduct", dtoSummary.getAmountProduct());
                params.put("chartData", listChartData);

                JasperPrint report = ReportService.generate(salesList, compileReport, params);
                ReportViewer.show(report);
            } catch (DAOException | JRException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("Ha ocurrido un error al generar el "
                        + "reporte", salesReport));
            }
        });
    }

    private DocumentListener documentListenerEvent() {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtils.filterTable(salesReport.getTxt_search(), salesReport.getJtbl_sales(), "Buscar Venta");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtils.filterTable(salesReport.getTxt_search(), salesReport.getJtbl_sales(), "Buscar Venta");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtils.filterTable(salesReport.getTxt_search(), salesReport.getJtbl_sales(), "Buscar Venta");
            }
        };
    }

}
