package com.hector.papeleriasaas.model.dto.user;

import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;

public class DTOUserDetails {

    private Integer id;
    private Integer identification;
    private String name;
    private String user;
    private Roles role;
    private Status status;

    public DTOUserDetails() {

    }

    public DTOUserDetails(Builder builder) {
        this.id = builder.id;
        this.identification = builder.identification;
        this.name = builder.name;
        this.user = builder.user;
        this.role = builder.role;
        this.status = builder.status;
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

    public String getUser() {
        return user;
    }

    public Roles getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private Integer id;
        private Integer identification;
        private String name;
        private String user;
        private Roles role;
        private Status status;

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

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder role(Roles role) {
            this.role = role;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOUserDetails build() {
            return new DTOUserDetails(this);
        }

    }

}
