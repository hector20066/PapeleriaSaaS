package com.hector.papeleriasaas.model.entities;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Provider {

    private int nit;
    private String rSocial;
    private String address;
    private String phone;
    private String email;

    public Provider() {

    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getRSocial() {
        return rSocial;
    }

    public void setRSocial(String rSocial) {
        this.rSocial = rSocial;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

