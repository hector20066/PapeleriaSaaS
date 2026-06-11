package com.hector.papeleriasaas.model.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSaleDetails {

    private int numSale;
    private int productId;
    private String productDescription;
    private int quantitySold;
    private LocalDate date;
    private String customer;
    private BigDecimal totalSale;

    public DTOSaleDetails() {

    }

    public DTOSaleDetails(Builder builder) {
        this.numSale = builder.numSale;
        this.productId = builder.productId;
        this.productDescription = builder.productDescription;
        this.quantitySold = builder.quantitySold;
        this.date = builder.date;
        this.customer = builder.customer;
        this.totalSale = builder.totalSale;
    }

    public int getNumSale() {
        return numSale;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public static class Builder {

        private int numSale;
        private int productId;
        private String productDescription;
        private int quantitySold;
        private LocalDate date;
        private String customer;
        private BigDecimal totalSale;

        public Builder numSale(int numSale) {
            this.numSale = numSale;
            return this;
        }

        public Builder productId(int productId) {
            this.productId = productId;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public Builder quantitySold(int quantitySold) {
            this.quantitySold = quantitySold;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder customer(String customer) {
            this.customer = customer;
            return this;
        }

        public Builder totalSale(BigDecimal totalSale) {
            this.totalSale = totalSale;
            return this;
        }

        public DTOSaleDetails build() {
            return new DTOSaleDetails(this);
        }

    }

}
