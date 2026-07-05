package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.business.DashboardDataCalculator;
import com.hector.papeleriasaas.controller.builder.ControllerDashboardBuilder;
import com.hector.papeleriasaas.controller.dto.DTOChartSales;
import com.hector.papeleriasaas.controller.dto.DTOReportSummary;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.executor.ObserverExecutor;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.SalesFacade;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.observer.Observer;
import com.hector.papeleriasaas.utils.MessageUtils;
import com.hector.papeleriasaas.view.content.panels.companies.Dashboard;
import com.hector.papeleriasaas.view.swing.model.ModelChart;

import java.awt.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ControllerDashboard implements Observer {

    private final Dashboard dashboard;

    private final ProductsFacade daoProducts;
    private final SalesFacade daoSales;

    public ControllerDashboard(ControllerDashboardBuilder builder) {
        this.dashboard = builder.getDashboard();
        this.daoProducts = builder.getDaoProducts();
        this.daoSales = builder.getDaoSales();

        builder.getSubject().register(this);
    }

    @Override
    public void update() {
        ObserverExecutor.execute(() -> {
            try {
                List<DTOSaleDetails> salesList = daoSales.findByAll();
                List<DTOProductDetails> productsList = daoProducts.findByAll();
                Optional<DTOProductBestSelling> bestSelling = daoProducts.bestSelling();

                if (bestSelling.isEmpty()) {
                    throw new ProductNotFoundException();
                }

                DTOReportSummary dto = getReportSummary(productsList, salesList, bestSelling.get());
                dashboard.updatePanel(dto.getTotalPurchases(), dto.getTotalSales(), dto.getTotalProfits(),
                        dto.getAmountProduct(), dto.getProductName());
                loadGraphicInfo(salesList);
            } catch (DAOException e) {
                /*SwingUtilities.invokeLater(() -> MessageUtils.errorMessage("No fue posible cargar el dashboard. "
                        + MessageUtils.userMessage(e), dashboard));*/
            }
        });
    }

    public static DTOReportSummary getReportSummary(List<DTOProductDetails> productsList, List<DTOSaleDetails> salesList,
                                                    DTOProductBestSelling bestSelling) {
        return new DTOReportSummary.Builder()
                .totalPurchases(DashboardDataCalculator.totalPurchases(productsList).floatValue())
                .totalSales(DashboardDataCalculator.totalSales(salesList).floatValue())
                .totalProfits(DashboardDataCalculator.totalProfits(salesList, productsList).floatValue())
                .productName(bestSelling.getDescription())
                .amountProduct(bestSelling.getTimesSold())
                .build();
    }

    private void loadGraphicInfo(List<DTOSaleDetails> listSales) {
        try {
            List<DTOChartSales> chartData = getChartSalesData(listSales, LocalDate.now());
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

    public static List<DTOChartSales> getChartSalesData(List<DTOSaleDetails> listSales, LocalDate today) {
        Set<Integer> processedSales = new HashSet<>();

        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        int[] salesMonth = new int[12];

        for (DTOSaleDetails sale : listSales) {
            int numSale = sale.getNumSale();

            if (!processedSales.contains(numSale) && sale.getDate().getYear() == currentYear) {
                int month = sale.getDate().getMonthValue();
                salesMonth[month - 1]++;
                processedSales.add(numSale);
            }
        }

        List<DTOChartSales> chartData = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int monthIndex = ((currentMonth - 1) - (4 - i) + 12) % 12;
            String monthName = Month.of(monthIndex + 1).getDisplayName(java.time.format.TextStyle.FULL,
                            Locale.of("es"));

            chartData.add(new DTOChartSales(monthName, salesMonth[monthIndex]));
        }

        return chartData;
    }

}
