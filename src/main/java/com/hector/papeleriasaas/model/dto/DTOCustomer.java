package com.hector.papeleriasaas.model.dto;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOCustomer {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private Integer companyId;

    public DTOCustomer() {

    }

    public DTOCustomer(Builder builder) {
        this.id = builder.identification;
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.companyId = builder.companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public static class Builder {

        private Integer identification;
        private String name;
        private String phone;
        private String email;
        private Integer companyId;

        public Builder identification(Integer identification) {
            this.identification = identification;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public DTOCustomer build() {
            return new DTOCustomer(this);
        }

    }
}
