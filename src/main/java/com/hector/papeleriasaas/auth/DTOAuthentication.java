package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.enums.Status;

public class DTOLogin {

    private Integer userId;
    private String user;
    private String passwd;
    private Status userStatus;
    private Integer companyId;
    private Status companyStatus;

    public DTOLogin() {

    }

    public DTOLogin(Builder builder) {
        this.userId = builder.userId;
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.userStatus = builder.userStatus;
        this.companyId = builder.companyId;
        this.companyStatus = builder.companyStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public Status getCompanyStatus() {
        return companyStatus;
    }

    public static class Builder {

        private Integer userId;
        private String user;
        private String passwd;
        private Status userStatus;
        private Integer companyId;
        private Status companyStatus;

        public Builder userId(Integer userId) {
            this.userId = userId;
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

        public Builder userStatus(Status userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder companyStatus(Status companyStatus) {
            this.companyStatus = companyStatus;
            return this;
        }

        public DTOLogin build() {
            return new DTOLogin(this);
        }

    }

}
