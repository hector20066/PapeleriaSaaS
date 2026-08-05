package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;

public class SessionImpl implements Session {

    private DTOUserDetails user;
    private DTOCompanyDetails company;

    public SessionImpl() {
    }

    @Override
    public void login(DTOUserDetails user, DTOCompanyDetails company) {
        this.user = user;
        this.company = company;
    }

    @Override
    public void logout() {
        user = null;
        company = null;
    }

    @Override
    public DTOUserDetails getUser() {
        return user;
    }

    @Override
    public DTOCompanyDetails getCompany() {
        return company;
    }

    @Override
    public Integer getCompanyId() {
        return company == null ? null : company.getId();
    }

    @Override
    public Integer getUserId() {
        return user.getId();
    }

}
