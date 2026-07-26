package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.enums.Status;

public class DTOAuthenticationRoot {

    private Integer userId;
    private String user;
    private String passwd;
    private Status status;

    public DTOAuthenticationRoot() {

    }

    public DTOAuthenticationRoot(Builder builder) {
        this.userId = builder.userId;
        this.user = builder.user;
        this.passwd = builder.passwd;
        this.status = builder.status;
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

    public Status getStatus() {
        return status;
    }

    public static class Builder {

        private Integer userId;
        private String user;
        private String passwd;
        private Status status;

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

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public DTOAuthenticationRoot build() {
            return new DTOAuthenticationRoot(this);
        }

    }

}
