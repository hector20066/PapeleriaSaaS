package com.hector.papeleriasaas.model.dto.company;

import com.hector.papeleriasaas.model.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DTOCompanyDetails {

    private Integer id;
    private Integer nit;
    private String name;
    private String address;
    private String phone;
    private LocalDate registrationDate;
    private Status status;
    private String planName;
    private BigDecimal planPrice;

    public DTOCompanyDetails() {

    }

    public DTOCompanyDetails(Builder builder) {
        this.id = builder.id;
        this.nit = builder.nit;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.registrationDate = builder.registrationDate;
        this.status = builder.status;
        this.planName = builder.planName;
        this.planPrice = builder.planPrice;
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

    public Status getStatus() {
        return status;
    }

    public String getPlanName() {
        return planName;
    }

    public BigDecimal getPlanPrice() {
        return planPrice;
    }

    public static class Builder {

        private Integer id;
        private Integer nit;
        private String name;
        private String address;
        private String phone;
        private LocalDate registrationDate;
        private Status status;
        private String planName;
        private BigDecimal planPrice;

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

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder planName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder planPrice(BigDecimal planPrice) {
            this.planPrice = planPrice;
            return this;
        }

        public DTOCompanyDetails build() {
            return new DTOCompanyDetails(this);
        }

    }

}
