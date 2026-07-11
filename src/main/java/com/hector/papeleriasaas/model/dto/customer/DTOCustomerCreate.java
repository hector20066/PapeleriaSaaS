package com.hector.papeleriasaas.model.dto.customer;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOCustomerCreate {

    private Integer identification;
    private String name;
    private String phone;
    private String email;
    private Integer companyId;

    public DTOCustomerCreate() {

    }

    public DTOCustomerCreate(Builder builder) {
        this.identification = builder.identification;
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
        this.companyId = builder.companyId;
    }

    public Integer getIdentification() {
        return identification;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCompanyId() {
        return companyId;
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

        public DTOCustomerCreate build() {
            return new DTOCustomerCreate(this);
        }

    }

}
