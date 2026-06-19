package com.hector.papeleriasaas.model.dto.invoice;

public class DTOInvoiceCreate {

    private Integer saleId;
    private Integer companyId;

    public DTOInvoiceCreate() {

    }

    public DTOInvoiceCreate(Integer saleId, Integer companyId) {
        this.saleId = saleId;
        this.companyId = companyId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
