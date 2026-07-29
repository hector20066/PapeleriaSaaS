package com.hector.papeleriasaas.model.dto.remembertokens;

import java.time.LocalDateTime;

public class DTORememberTokenCreate {

    private String token;
    private Integer userId;
    private Integer companyId;
    private LocalDateTime expiresAt;
    private String deviceName;

    public DTORememberTokenCreate() {

    }

    public DTORememberTokenCreate(Builder builder) {
        this.token = builder.token;
        this.userId = builder.userId;
        this.companyId = builder.companyId;
        this.expiresAt = builder.expiresAt;
        this.deviceName = builder.deviceName;
    }

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public static class Builder {

        private String token;
        private Integer userId;
        private Integer companyId;
        private LocalDateTime expiresAt;
        private String deviceName;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public DTORememberTokenCreate build() {
            return new DTORememberTokenCreate(this);
        }

    }

}
