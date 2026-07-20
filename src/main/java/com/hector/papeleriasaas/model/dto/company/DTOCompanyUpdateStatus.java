package com.hector.papeleriasaas.model.dto.company;

import com.hector.papeleriasaas.model.enums.Status;

public class DTOCompanyUpdateStatus {

    private Integer id;
    private Status status;

    public DTOCompanyUpdateStatus() {

    }

    public DTOCompanyUpdateStatus(Integer id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
