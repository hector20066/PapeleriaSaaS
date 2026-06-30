package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.Status;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class CompanyTest {

    Company company;
    Plan plan;

    @BeforeEach
    void createInstance() {
        this.plan = new Plan.Builder()
                .id(12345)
                .build();
        
        this.company = new Company.Builder()
                .id(1066726477)
                .nit(12345)
                .name("Papelería SaaS")
                .address("Tr 24 16-25")
                .phone("3137762626")
                .email("papeleriasaas@gmail.com")
                .registrationDate(LocalDate.parse("2026-06-22"))
                .status(Status.ACTIVE)
                .plan(plan)
                .build();

        company.addUser(new User.Builder().build());
        company.addProduct(new Product.Builder().build());
        company.addProvider(new Provider.Builder().build());
        company.addCustomer(new Customer.Builder().build());
        company.addPurchase(new Purchase.Builder().build());
        company.addSale(new Sale.Builder().build());
        company.addDebt(new Debt());
        company.addInvoice(new Invoice());
        company.addCompanyPayments(new CompanyPayment.Builder().build());
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
                () -> assertEquals("Papelería SaaS", company.getName(), () -> "El nombre no coincide"),
                () -> assertEquals("Tr 24 16-25", company.getAddress(), () -> "La dirección no es la esperada"),
                () -> assertEquals("3137762626", company.getPhone(), () -> "El numero de teléfono no es el esperado"),
                () -> assertEquals("papeleriasaas@gmail.com", company.getEmail(), () -> "El correo no es el esperado"),
                () -> assertEquals(LocalDate.parse("2026-06-22"), company.getRegistrationDate(), () -> "La fecha de registro no es la esperada"),
                () -> assertEquals(Status.ACTIVE, company.getStatus(), () -> "El estado no es el esperado"),
                () -> assertEquals(12345, company.getPlan().getId(), () -> "El id del plan no es el esperado")
        );
    }

    @Nested
    @DisplayName("Validar el manejo de las listas")
    class ListDataTest {

        @Test
        @DisplayName("Validar que no sean nulas")
        void testListNotNullable() {
            assertAll(
                    () -> assertNotNull(company.getUsers(), () -> "La lista de los usuarios es nula"),
                    () -> assertNotNull(company.getProducts(), () -> "La lista de los productos es nula"),
                    () -> assertNotNull(company.getProviders(), () -> "La lista de los Proveedores es nula"),
                    () -> assertNotNull(company.getCustomers(), () -> "La lista de los clientes es nula"),
                    () -> assertNotNull(company.getPurchases(), () -> "La lista de los compras es nula"),
                    () -> assertNotNull(company.getSales(), () -> "La lista de las ventas es nula"),
                    () -> assertNotNull(company.getDebts(), () -> "La lista de las deudas es nula"),
                    () -> assertNotNull(company.getInvoices(), () -> "La lista de facturas es nula"),
                    () -> assertNotNull(company.getCompanyPayments(), () -> "La lista de pagos es nula")
            );
        }

        @Test
        @DisplayName("Testear que no estén vacías")
        void testListNotEmpty() {
            assertAll(
                    () -> assertFalse(company.getUsers().isEmpty(), () -> "La lista de los usuarios esta vacía"),
                    () -> assertFalse(company.getProducts().isEmpty(), () -> "La lista de los productos esta vacía"),
                    () -> assertFalse(company.getProviders().isEmpty(), () -> "La lista de los Proveedores esta vacía"),
                    () -> assertFalse(company.getCustomers().isEmpty(), () -> "La lista de los clientes esta vacía"),
                    () -> assertFalse(company.getPurchases().isEmpty(), () -> "La lista de los compras esta vacía"),
                    () -> assertFalse(company.getSales().isEmpty(), () -> "La lista de las ventas esta vacía"),
                    () -> assertFalse(company.getDebts().isEmpty(), () -> "La lista de las deudas esta vacía"),
                    () -> assertFalse(company.getInvoices().isEmpty(), () -> "La lista de facturas esta vacía"),
                    () -> assertFalse(company.getCompanyPayments().isEmpty(), () -> "La lista de pagos esta vacía")
            );
        }

        @Test
        @DisplayName("Testear el tamaño")
        void testListSize() {
            company.addUser(new User.Builder().build())
                    .addUser(new User.Builder().build());
            company.addProvider(new Provider.Builder().build())
                    .addProvider(new Provider.Builder().build());
            company.addCustomer(new Customer.Builder().build());
            company.addSale(new Sale.Builder().build());
            company.addDebt(new Debt())
                    .addDebt(new Debt())
                    .addDebt(new Debt());
            company.addInvoice(new Invoice());
            company.addCompanyPayments(new CompanyPayment.Builder().build());

            assertAll(
                    () -> assertEquals(3, company.getUsers().size(), () -> "El tamaño de la lista de Usuarios no es el esperado."),
                    () -> assertEquals(1, company.getProducts().size(), () -> "El tamaño de la lista de Productos no es el esperado."),
                    () -> assertEquals(3, company.getProviders().size(), () -> "El tamaño de la lista de Proveedores no es el esperado."),
                    () -> assertEquals(2, company.getCustomers().size(), () -> "El tamaño de la lista de Clientes no es el esperado."),
                    () -> assertEquals(1, company.getPurchases().size(), () -> "El tamaño de la lista de Compras no es el esperado."),
                    () -> assertEquals(2, company.getSales().size(), () -> "El tamaño de la lista de Ventas no es el esperado."),
                    () -> assertEquals(4, company.getDebts().size(), () -> "El tamaño de la lista de Deudas no es el esperado."),
                    () -> assertEquals(2, company.getInvoices().size(), () -> "El tamaño de la lista de Facturas no es el esperado."),
                    () -> assertEquals(2, company.getCompanyPayments().size(), () -> "El tamaño de la lista de Pagos no es el esperado.")
            );
        }

    }

}
