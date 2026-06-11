package com.hector.papeleriasaas.model.dto;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOSupplier {

    private int nit;
    private String rSocial;
    private String address;
    private String phone;
    private String email;

    public DTOSupplier() {

    }

    public DTOSupplier(Builder builder) {
        this.nit = builder.nit;
        this.rSocial = builder.rSocial;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public int getNit() {
        return nit;
    }

    public String getRSocial() {
        return rSocial;
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

        private int nit;
        private String rSocial;
        private String address;
        private String phone;
        private String email;

        public Builder nit(int nit) {
            this.nit = nit;
            return this;
        }

        public Builder rSocial(String rSocial) {
            this.rSocial = rSocial;
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

        public DTOSupplier build() {
            return new DTOSupplier(this);
        }

    }

}
