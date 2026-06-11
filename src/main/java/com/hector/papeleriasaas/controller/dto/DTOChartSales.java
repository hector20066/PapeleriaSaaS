package com.hector.papeleriasaas.controller.dto;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOChartSales {

    private final String month;
    private final int sales;

    public DTOChartSales(String month, int sales) {
        this.month = month;
        this.sales = sales;
    }

    public String getMonth() {
        return month;
    }

    public int getSales() {
        return sales;
    }

}
