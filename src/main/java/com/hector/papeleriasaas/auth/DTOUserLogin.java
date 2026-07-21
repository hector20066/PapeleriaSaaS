package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;

public class DTOUserLogin {

    private Integer id;
    private String user;
    private String passwd;
    private Roles role;
    private Status status;
    private Integer companyId;
    private String companyName;

    public DTOUserLogin() {

    }

    public DTOUserLogin(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.role = builder.role;
        this.status = builder.status;
        this.companyId = builder.companyId;
        this.companyName = builder.companyName;
    }

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    public Roles getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static class Builder {

        private Integer id;
        private String user;
        private String passwd;
        private Roles role;
        private Status status;
        private Integer companyId;
        private String companyName;

        public Builder id(Integer id) {
            this.id = id;
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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public DTOUserLogin build() {
            return new DTOUserLogin(this);
        }

    }

}
