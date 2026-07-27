package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;

public interface Session {

    void login(DTOUserDetails user, DTOCompanyDetails dtoCompanyDetails);
    void logout();
    DTOUserDetails getUser();
    DTOCompanyDetails getCompany();
    Integer getUserId();
    Integer getCompanyId();

}
