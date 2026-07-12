package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;

public class Session {

    private static DTOUserDetails user;
    private static DTOCompanyDetails company;

    private Session() {}

    public static void login(DTOUserDetails user, DTOCompanyDetails company) {
        Session.user = user;
        Session.company = company;
    }

    public static DTOUserDetails getUser() {
        return user;
    }

    public static DTOCompanyDetails getCompany() {
        return company;
    }

    /*public static int getCompanyId() {
        return company.getId();
    }

    public static int getUserId() {
        return user.getId();
    }*/

    public static void logout() {
        user = null;
        company = null;
    }

}
