package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.controller.builder.ControllerDashboardBuilder;
import com.hector.papeleriasaas.controller.dto.DTOChartSales;
import com.hector.papeleriasaas.controller.dto.DTOReportSummary;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.mapper.DashboardCompanyMapper;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.validator.CompanyDashboardValidator;
import com.hector.papeleriasaas.view.content.panels.companies.Dashboard;
import com.hector.papeleriasaas.view.swing.model.ModelChart;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboard implements Observer {

    private final Dashboard dashboard;

    private final ProductsFacade productsFacade;
    private final SalesFacade salesFacade;

    public ControllerDashboard(ControllerDashboardBuilder builder) {
        this.dashboard = builder.getDashboard();
        this.productsFacade = builder.getDaoProducts();
        this.salesFacade = builder.getDaoSales();

        builder.getSubject().register(this);
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> salesList = salesFacade.findByAll();
                List<DTOProductDetails> productsList = productsFacade.findByAll();
                Optional<DTOProductBestSelling> bestSelling = productsFacade.bestSelling();

                CompanyDashboardValidator.bestSellingEmptyValidation(bestSelling);

                DTOReportSummary dto = DashboardCompanyMapper.getReportSummary(productsList, salesList, bestSelling.get());
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
