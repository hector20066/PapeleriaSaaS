package com.hector.papeleriasaas.model.entities;

public class Person {

    protected Integer id;
    protected Integer identification;
    protected String name;
    
    public Person() {
        
    }

    public Person(Integer id, Integer identification, String name) {
        this.id = id;
        this.identification = identification;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
