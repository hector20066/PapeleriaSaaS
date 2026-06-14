package com.hector.papeleriasaas.model.entities;

import java.time.LocalDate;

import com.hector.papeleriasaas.model.enums.Status;

public class Company {

    private int id;
    private int nit;
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate registration_date;
    private Status status;
    
}
