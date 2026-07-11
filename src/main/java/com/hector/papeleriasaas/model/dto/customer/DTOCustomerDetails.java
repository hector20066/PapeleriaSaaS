package com.hector.papeleriasaas.model.dto.customer;

public class DTOCustomerDetails {

    private Integer id;
    private Integer identification;
    private String name;
    private String phone;
    private String email;

    public DTOCustomerDetails() {

    }

    public DTOCustomerDetails(Builder builder) {
        this.id = builder.id;
        this.identification = builder.identification;
        this.name = builder.name;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public Integer getId() {
        return id;
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

    public static class Builder {

        private Integer id;
        protected Integer identification;
        protected String name;
        protected String phone;
        protected String email;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

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

        public DTOCustomerDetails build() {
            return new DTOCustomerDetails(this);
        }

    }

}
