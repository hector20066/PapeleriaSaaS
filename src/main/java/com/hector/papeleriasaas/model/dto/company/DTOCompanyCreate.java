package com.hector.papeleriasaas.model.dto.company;

import com.hector.papeleriasaas.model.enums.Status;

import java.time.LocalDate;

public class DTOCompanyCreate {

    private Integer nit;
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    private Status status;
    private Integer planId;

    public DTOCompanyCreate() {

    }

    public DTOCompanyCreate(Builder builder) {
        this.nit = builder.nit;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.registrationDate = builder.registrationDate;
        this.status = builder.status;
        this.planId = builder.planId;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public static class Builder {

        private Integer nit;
        private String name;
        private String address;
        private String phone;
        private String email;
        private LocalDate registrationDate;
        private Status status;
        private Integer planId;

        public Builder nit(Integer nit) {
            this.nit = nit;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder planId(Integer planId) {
            this.planId = planId;
            return this;
        }

        public DTOCompanyCreate build() {
            return new DTOCompanyCreate(this);
        }

    }

}
