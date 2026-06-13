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

    private int purchaseNumber;
    private Provider provider;
    private LocalDate date;
    private BigDecimal total;
    private List<PurchaseDetail> purchaseDetails;

    public Purchase() {
        this.purchaseDetails = new ArrayList<>();
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(int purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public void addPurchaseDetails(PurchaseDetail purchaseDetail) {
        this.purchaseDetails.add(purchaseDetail);
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

}
