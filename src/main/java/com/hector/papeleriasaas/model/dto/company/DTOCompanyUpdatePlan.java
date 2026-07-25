package com.hector.papeleriasaas.model.dto.company;

public class DTOCompanyUpdatePlan {

    private Integer id;
    private Integer planId;

    public DTOCompanyUpdatePlan() {

    }

    public DTOCompanyUpdatePlan(Integer id, Integer planId) {
        this.id = id;
        this.planId = planId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

}
