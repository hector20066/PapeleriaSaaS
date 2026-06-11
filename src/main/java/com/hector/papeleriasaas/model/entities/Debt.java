package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.StatusDebt;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Debt {

    private Sale sale;
    private BigDecimal totalDebt;
    private BigDecimal totalAmountPay;
    private StatusDebt status;

    public Debt() {

    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(BigDecimal totalDebt) {
        this.totalDebt = totalDebt;
    }

    public BigDecimal getTotalAmountPay() {
        return totalAmountPay;
    }

    public void setTotalAmountPay(BigDecimal totalAmountPay) {
        this.totalAmountPay = totalAmountPay;
    }

    public StatusDebt getStatus() {
        return status;
    }

    public void setStatus(StatusDebt status) {
        this.status = status;
    }

}
