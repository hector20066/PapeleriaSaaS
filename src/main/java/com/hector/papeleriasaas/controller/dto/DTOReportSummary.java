package com.hector.papeleriasaas.controller.dto;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOReportSummary {

    private float totalPurchases;
    private float totalSales;
    private float totalProfits;
    private String productName;
    private int amountProduct;

    public DTOReportSummary() {

    }

    public DTOReportSummary(Builder builder) {
        this.totalPurchases = builder.totalPurchases;
        this.totalSales = builder.totalSales;
        this.totalProfits = builder.totalProfits;
        this.productName = builder.productName;
        this.amountProduct = builder.amountProduct;
    }

    public float getTotalPurchases() {
        return totalPurchases;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public float getTotalProfits() {
        return totalProfits;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public static class Builder {

        private float totalPurchases;
        private float totalSales;
        private float totalProfits;
        private String productName;
        private int amountProduct;

        public Builder totalPurchases(float totalPurchases) {
            this.totalPurchases = totalPurchases;
            return this;
        }

        public Builder totalSales(float totalSales) {
            this.totalSales = totalSales;
            return this;
        }

        public Builder totalProfits(float totalDebts) {
            this.totalProfits = totalDebts;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder amountProduct(int amountProduct) {
            this.amountProduct = amountProduct;
            return this;
        }

        public DTOReportSummary build() {
            return new DTOReportSummary(this);
        }
    }

}
