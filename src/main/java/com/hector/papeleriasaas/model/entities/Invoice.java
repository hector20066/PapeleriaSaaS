package com.hector.papeleriasaas.model.entities;

import java.time.LocalDate;

public class Invoice {

    private Integer id;
    private Integer numInvoice;
    private LocalDate issueDate;

    public Invoice() {

    }

    public Invoice(Integer id, Integer numInvoice, LocalDate issueDate) {
        this.id = id;
        this.numInvoice = numInvoice;
        this.issueDate = issueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumInvoice() {
        return numInvoice;
    }

    public void setNumInvoice(Integer numInvoice) {
        this.numInvoice = numInvoice;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

}
