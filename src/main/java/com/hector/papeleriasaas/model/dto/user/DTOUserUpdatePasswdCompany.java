package com.hector.papeleriasaas.model.dto.user;

public class DTOUserUpdatePasswdCompany {

    private String passwd;
    private Integer id;
    private Integer companyId;

    public DTOUserUpdatePasswdCompany() {

    }

    public DTOUserUpdatePasswdCompany(String passwd, Integer id, Integer companyId) {
        this.passwd = passwd;
        this.id = id;
        this.companyId = companyId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
