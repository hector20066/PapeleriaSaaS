package com.hector.papeleriasaas.model.dto.user;

public class DTOUserUpdatePasswd {

    private String passwd;
    private Integer id;

    public DTOUserUpdatePasswd() {

    }

    public DTOUserUpdatePasswd(String passwd, Integer id) {
        this.passwd = passwd;
        this.id = id;
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

}
