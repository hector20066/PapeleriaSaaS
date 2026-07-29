package com.hector.papeleriasaas.model.entities;

import java.time.LocalDateTime;

public class RememberTokens {

    private Integer id;
    private String token;
    private Integer userId;
    private Integer companyId;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime lastUsedAt;
    private String deviceName;

    public RememberTokens() {

    }

    public RememberTokens(Builder builder) {

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
        private LocalDateTime createdAt;
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

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
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

        public RememberTokens build() {
            return new RememberTokens(this);
        }

    }

}
