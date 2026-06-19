package com.hector.papeleriasaas.model.dto.user;

import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;

public class DTOUserCreate {

    private Integer identification;
    private String name;
    private String user;    
    private String passwd;
    private Roles role;
    private Status status;
    private Integer companyId;

    public DTOUserCreate() {

    }

    public DTOUserCreate(Builder builder) {
        this.identification = builder.identification;
        this.name = builder.name;
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.role = builder.role;
        this.status = builder.status;
        this.companyId = builder.companyId;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public static class Builder {

        private Integer identification;
        private String name;
        private String user;
        private String passwd;
        private Roles role;
        private Status status;
        private Integer companyId;

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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public DTOUserCreate build() {
            return new DTOUserCreate(this);
        }

    }

}
