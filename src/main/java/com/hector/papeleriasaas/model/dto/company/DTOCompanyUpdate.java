package com.hector.papeleriasaas.model.dto.company;

public class DTOCompanyUpdate {

    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String email;

    public DTOCompanyUpdate() {

    }

    public DTOCompanyUpdate(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public Integer getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public static class Builder {

        private Integer id;
        private String name;
        private String address;
        private String phone;
        private String email;

        public Builder id(Integer id) {
            this.id = id;
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

        public DTOCompanyUpdate build() {
            return new DTOCompanyUpdate(this);
        }

    }

}
