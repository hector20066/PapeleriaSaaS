package com.hector.papeleriasaas.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Customer extends Person {

    private String phone;
    private String email;
    private final List<Sale> sales;

    public Customer() {
        this.sales = new ArrayList<>();
    }

    public Customer(Builder builder) {
        super(builder.id, builder.identification, builder.name);
        this.phone = builder.phone;
        this.email = builder.email;

        this.sales = new ArrayList<>();
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

    public List<Sale> getSales() {
        return sales;
    }

    public Customer addSale(Sale sale) {
        this.sales.add(sale);
        return this;
    }

    public static class Builder {

        private Integer id;
        private Integer identification;
        private String name;
        private String phone;
        private String email;

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

        public Customer build() {
            return new Customer(this);
        }

    }

}

