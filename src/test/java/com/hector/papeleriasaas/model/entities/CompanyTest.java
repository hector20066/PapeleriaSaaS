package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.BillingCycle;
import com.hector.papeleriasaas.model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company company;
    Plan plan;

    @BeforeEach
    void createInstance() {
        this.plan = new Plan.Builder()
                .id(12345)
                .name("Plan Premium")
                .amount(new BigDecimal("54000.00"))
                .description("Incluye: \n 1. Acceso a multiples funciones.\n 2. Un maximo de 4 usuarios registrados.")
                .billingCycle(BillingCycle.MONTHLY)
                .build();
        
        this.company = new Company.Builder()
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
    }

    @Test
    @DisplayName("Testear que los datos no sean null")
    void testDataNotNull() {
        assertAll(
                () -> assertNotNull(company, () -> "La instancia no puede ser nula"),
                () -> assertNotNull(company.getNit(), () -> "El Nit de la empresa no puede estar vacio"),
                () -> assertNotNull(company.getId(), () -> "El id de la empresa no puede ser null"),
                () -> assertNotNull(company.getName(), () -> "El nombre de la empresa no puede estar vacio"),
                () -> assertNotNull(company.getStatus(), () -> "El estatus no puede ser nul"),
                () -> assertNotNull(company.getRegistrationDate(), () -> "La fecha de registro no puede estar vacia o ser nula"),
                () -> assertNotNull(company.getPlan(), () -> "El plan no puede ser nulo")
        );
    }

    @Test
    @DisplayName("Validar los datos recibidos")
    void testValidateData() {
        assertAll(
                () -> assertEquals(1066726477, company.getId(), () -> "El id con coincide con el esperado"),
                () -> assertEquals(12345, company.getNit(), () -> "El Nit no coincide con el valor esperado"),
                () -> assertEquals("Papeleria SaaS", company.getName(), () -> "El nombre no coincide"),
                () -> assertEquals("Tr 24 16-25", company.getAddress(), () -> "La direccion no es la esperada"),
                () -> assertEquals("3137762626", company.getPhone(), () -> "El numero de telefono no es el esperado"),
                () -> assertEquals("papeleriasaas@gmail.com", company.getEmail(), () -> "El correo no es el esperado"),
                () -> assertEquals(LocalDate.parse("2026-06-22"), company.getRegistrationDate(), () -> "La fecha de registro no es la esperada"),
                () -> assertEquals(Status.ACTIVE, company.getStatus(), () -> "El estado no es el esperado"),
                () -> assertEquals(12345, company.getPlan().getId(), () -> "El id del plan no es el esperado")
        );
    }

}
