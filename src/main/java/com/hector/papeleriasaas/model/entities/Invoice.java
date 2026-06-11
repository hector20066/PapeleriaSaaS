package com.hector.papeleriasaas.model.entities;

import java.time.LocalDate;

public class Invoice {

    private int numInvoice;
    private LocalDate date;

    public Invoice() {

    }

    public Invoice(int numInvoice, LocalDate date) {
        this.numInvoice = numInvoice;
        this.date = date;
    }

    public int getNumInvoice() {
        return numInvoice;
    }

    public void setNumInvoice(int numInvoice) {
        this.numInvoice = numInvoice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
