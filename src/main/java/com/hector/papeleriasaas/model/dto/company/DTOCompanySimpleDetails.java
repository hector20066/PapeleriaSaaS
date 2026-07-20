package com.hector.papeleriasaas.model.dto.company;

import java.time.LocalDate;

import com.hector.papeleriasaas.model.enums.Status;

public class DTOCompanySimpleDetails {

    private Integer id;
    private Integer nit;
    private String name;
    private String address;
    private String phone;
    private LocalDate registrationDate;
    private String planName;
    private LocalDate lastPayment;
    private LocalDate nextPayment;
    private Status status;

    public DTOCompanySimpleDetails() {

    }

    public DTOCompanySimpleDetails(Builder builder) {
        this.id = builder.id;
        this.nit = builder.nit;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.registrationDate = builder.registrationDate;
        this.planName = builder.planName;
        this.lastPayment = builder.lastPayment;
        this.nextPayment = builder.nextPayment;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNit() {
        return nit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getPlanName() {
        return planName;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public LocalDate getNextPayment() {
        return nextPayment;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private Integer id;
        private Integer nit;
        private String name;
        private String address;
        private String phone;
        private LocalDate registrationDate;
        private String planName;
        private LocalDate lastPayment;
        private LocalDate nextPayment;
        private Status status;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

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

        public Builder registrationDate(LocalDate registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder planName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder lastPayment(LocalDate lastPayment) {
            this.lastPayment = lastPayment;
            return this;
        }

        public Builder nextPayment(LocalDate nextPayment) {
            this.nextPayment = nextPayment;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOCompanySimpleDetails build() {
            return new DTOCompanySimpleDetails(this);
        }

    }

}
