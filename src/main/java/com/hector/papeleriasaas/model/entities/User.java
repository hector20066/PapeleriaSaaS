package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;

public class User extends Person {

    private String user;
    private String passwd;
    private Roles role;
    private Status status;

    public User() {

    }

    public User(Builder builder) {
        super(builder.id, builder.identification, builder.name);
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.role = builder.role;
        this.status = builder.status;
    } 

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Builder {

        private Integer id;
        private Integer identification;
        private String name;
        private String user;
        private String passwd;
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

        public Builder passwd(String passwd) {
            this.passwd = passwd;
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

        public User build() {
            return new User(this);
        }

    }

}

