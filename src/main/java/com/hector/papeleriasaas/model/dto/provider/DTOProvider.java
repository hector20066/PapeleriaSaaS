package com.hector.papeleriasaas.model.dto.provider;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProvider {

    private Integer id;
    private Integer nit;
    private String rSocial;
    private String address;
    private String phone;
    private String email;
    private Integer companyId;

    public DTOProvider() {

    }

    public DTOProvider(Builder builder) {
        this.nit = builder.nit;
        this.rSocial = builder.rSocial;
        this.address = builder.address;
        this.phone = builder.phone;
        this.email = builder.email;
        this.id = builder.id;
        this.companyId = builder.companyId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNit() {
        return nit;
    }

    public String getRSocial() {
        return rSocial;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public static class Builder {

        private Integer id;
        private Integer nit;
        private String rSocial;
        private String address;
        private String phone;
        private String email;
        private Integer companyId;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder nit(Integer nit) {
            this.nit = nit;
            return this;
        }

        public Builder rSocial(String rSocial) {
            this.rSocial = rSocial;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
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

        public Builder companyId(Integer companyId) {
            this.companyId = companyId;
            return this;
        }

        public DTOProvider build() {
            return new DTOProvider(this);
        }

    }

}
