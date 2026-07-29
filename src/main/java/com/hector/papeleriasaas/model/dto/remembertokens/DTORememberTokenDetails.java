package com.hector.papeleriasaas.model.dto.remembertokens;

import java.time.LocalDateTime;

public class DTORememberTokenDetails {

    private Integer id;
    private String token;
    private Integer userId;
    private Integer companyId;
    private LocalDateTime createAt;
    private LocalDateTime expiresAt;
    private LocalDateTime lastUsedAt;
    private String deviceName;

    public DTORememberTokenDetails() {

    }

    public DTORememberTokenDetails(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.userId = builder.userId;
        this.companyId = builder.companyId;
        this.createAt = builder.createAt;
        this.expiresAt = builder.expiresAt;
        this.lastUsedAt = builder.lastUsedAt;
        this.deviceName = builder.deviceName;
    }

    public Integer getId() {
        return id;
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public static class Builder {

        private Integer id;
        private String token;
        private Integer userId;
        private Integer companyId;
        private LocalDateTime createAt;
        private LocalDateTime expiresAt;
        private LocalDateTime lastUsedAt;
        private String deviceName;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

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

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Builder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder lastUsedAt(LocalDateTime lastUsedAt) {
            this.lastUsedAt = lastUsedAt;
            return this;
        }

        public Builder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public DTORememberTokenDetails build() {
            return new DTORememberTokenDetails(this);
        }

    }

}
