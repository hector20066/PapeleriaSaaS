package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.controller.dto.DTOChartSales;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DashboardCompanyMapper {

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
