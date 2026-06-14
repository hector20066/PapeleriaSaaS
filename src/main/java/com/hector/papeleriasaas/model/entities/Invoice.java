package com.hector.papeleriasaas.model.entities;

import java.time.LocalDate;

public class Invoice {

    private int id;
    private int numInvoice;
    private LocalDate issueDate;

    public Invoice() {

    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getNumInvoice() {
        return numInvoice;
    }

    public void setNumInvoice(int numInvoice) {
        this.numInvoice = numInvoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
