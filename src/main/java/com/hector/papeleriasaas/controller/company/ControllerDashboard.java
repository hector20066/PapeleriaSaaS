package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerDashboardBuilder;
import com.hector.papeleriasaas.controller.dto.DTOChartSales;
import com.hector.papeleriasaas.controller.dto.DTOReportSummary;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.mapper.DashboardCompanyMapper;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.SaleService;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.view.content.panels.companies.DashboardCompanyView;
import com.hector.papeleriasaas.view.swing.model.ModelChart;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboard implements Observer {

    private final DashboardCompanyView dashboard;

    private final ProductService productService;
    private final SaleService saleService;

    public ControllerDashboard(ControllerDashboardBuilder builder) {
        this.dashboard = builder.getDashboard();
        this.productService = builder.getProductService();
        this.saleService = builder.getSaleService();

        builder.getSubject().register(this);
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> salesList = saleService.findByAll();
                List<DTOProductDetails> productsList = productService.findByAll();
                DTOProductBestSelling bestSelling = productService.bestSelling();

                DTOReportSummary dto = DashboardCompanyMapper.getReportSummary(productsList, salesList, bestSelling);
                dashboard.updatePanel(dto.getTotalPurchases(), dto.getTotalSales(), dto.getTotalProfits(),
                        dto.getAmountProduct(), dto.getProductName());
                loadGraphicInfo(salesList);
            } catch (DAOException e) {
                SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el dashboard. "
                        + MessageUtils.userMessage(e), dashboard));
            }
        });
    }

    private void loadGraphicInfo(List<DTOSaleDetails> listSales) {
        try {
            List<DTOChartSales> chartData = DashboardCompanyMapper.getChartSalesData(listSales, LocalDate.now());
            dashboard.getLineChart().clear();
            dashboard.getLineChart().addLegend("Ventas Mensuales", new Color(174, 183, 191),
                    new Color(60, 73, 89));

            for (DTOChartSales item : chartData) {
                dashboard.getLineChart().addData(new ModelChart(item.getMonth(), new double[]{item.getSales()}));
            }

            dashboard.getLineChart().start();
        } catch (Exception e) {
            MessageUtils.errorMessage("No fue posible actualizar el gráfico de ventas.", dashboard);
        }
    }

}
