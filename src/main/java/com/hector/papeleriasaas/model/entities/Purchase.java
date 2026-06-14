package com.hector.papeleriasaas.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Purchase {

    private int id;
    private int purchaseNumber;
    private LocalDate date;
    private BigDecimal total;
    private Provider provider;
    private final List<PurchaseDetail> details;

    public Purchase() {
        this.details = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<PurchaseDetail> getDetails() {
        return details;
    }

    public Purchase addPurchaseDetails(PurchaseDetail detail) {
        this.details.add(detail);
        return this;
    }

}
