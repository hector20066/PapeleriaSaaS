package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Debt {

    private int id;
    private BigDecimal totalDebt;
    private PaymentStatus status;

    public Debt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(BigDecimal totalDebt) {
        this.totalDebt = totalDebt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

}
