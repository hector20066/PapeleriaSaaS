package com.hector.papeleriasaas.model.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.hector.papeleriasaas.model.enums.Status;

class CompanyTest {

    @Test
    @DisplayName("Probar la creacion a traves del Builder")
    void createCompanyTest() {
        Plan plan = new Plan();

        Company company = new Company.Builder()
            .id(1066726477)
            .nit(12345)
            .name("Papeleria SaaS")
            .address("Tr 24 16-25")
            .phone("3137762626")
            .email("papeleriasaas@gmail.com")
            .registrationDate(LocalDate.parse("2026-06-22"))
            .status(Status.ACTIVE)
            .plan(plan)
            .build();

        assertAll(
                () -> assertEquals("Papeleria SaaS", company.getName()),
                () -> assertNotNull(company, () -> "La instancia no puede ser nula"),
                () -> assertNotNull(company.getNit(), () -> "El Nit de la empresa no puede estar vacio")
        );
    }

}
