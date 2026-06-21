package com.hector.papeleriasaas.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Provider {

    private Integer id;
    private Integer nit;
    private String rSocial;
    private String address;
    private String phone;
    private String email;
    private final List<Product> products;

    public Provider() {
        this.products = new ArrayList<>();
    }

    public Provider(Builder builder) {
        this();
        this.id = builder.id;
        this.nit = builder.nit;
        this.rSocial = builder.rSocial;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getrSocial() {
        return rSocial;
    }

    public void setrSocial(String rSocial) {
        this.rSocial = rSocial;
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

    public List<Product> getProducts() {
        return products;
    }

    public Provider addProduct(Product product) {
        this.products.add(product);
        return this;
    }

    public static class Builder {
        
        private Integer id;
        private Integer nit;
        private String rSocial;
        private String address;
        private String phone;
        private String email;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nit(Integer nit) {
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

        public Provider build() {
            return new Provider(this);
        }

    }

}

