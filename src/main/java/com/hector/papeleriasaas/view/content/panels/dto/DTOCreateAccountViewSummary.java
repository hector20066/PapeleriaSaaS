package com.hector.papeleriasaas.view.content.panels.dto;

import java.math.BigDecimal;

public class DTOCreateAccountViewSummary {

    private String companyNit;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String companyEmail;

    private String planName;
    private BigDecimal planPrice;

    private String userId;
    private String personName;
    private String username;
    private String role;

    public DTOCreateAccountViewSummary(Builder builder) {
        this.companyNit = builder.companyNit;
        this.companyName = builder.companyName;
        this.companyPhone = builder.companyPhone;
        this.companyAddress = builder.companyAddress;
        this.companyEmail = builder.companyEmail;

        this.planName = builder.planName;
        this.planPrice = builder.planPrice;

        this.userId = builder.userId;
        this.personName = builder.personName;
        this.username = builder.username;
        this.role = builder.role;
    }

    public String getCompanyNit() {
        return companyNit;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public String getPlanName() {
        return planName;
    }

    public BigDecimal getPlanPrice() {
        return planPrice;
    }

    public String getUserId() {
        return userId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public static class Builder {

        private String companyNit;
        private String companyName;
        private String companyPhone;
        private String companyAddress;
        private String companyEmail;

        private String planName;
        private BigDecimal planPrice;

        private String userId;
        private String personName;
        private String username;
        private String role;

        public Builder companyNit(String companyNit) {
            this.companyNit = companyNit;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public Builder companyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
            return this;
        }

        public Builder companyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
            return this;
        }

        public Builder companyEmail(String companyEmail) {
            this.companyEmail = companyEmail;
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

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder personName(String personName) {
            this.personName = personName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public DTOCreateAccountViewSummary build() {
            return new DTOCreateAccountViewSummary(this);
        }
        
    }

}
