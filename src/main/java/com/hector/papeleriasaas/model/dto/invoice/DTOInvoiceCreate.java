package com.hector.papeleriasaas.model.dto.invoice;

public class DTOInvoiceCreate {

    private int numSale;

    public DTOInvoiceCreate() {

    }

    public DTOInvoiceCreate(int numSale) {
        this.numSale = numSale;
    }

    public int getNumSale() {
        return numSale;
    }

    public void setNumSale(int numSale) {
        this.numSale = numSale;
    }

}
