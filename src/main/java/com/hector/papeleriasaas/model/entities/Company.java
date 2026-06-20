package com.hector.papeleriasaas.model.entities;

import com.hector.papeleriasaas.model.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Company {

    private Integer id;
    private Integer nit;
    private String name;
    private String address;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    private Status status;
    private Plan plan;
    private final List<User> users;
    private final List<Product> products;
    private final List<Provider> providers;
    private final List<Customer> customers;
    private final List<Purchase> purchases;
    private final List<Sale> sales;
    private final List<Debt> debts;
    private final List<Invoice> invoices;
    private final List<CompanyPayment> companyPayments;

    public Company() {
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        this.providers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.purchases = new ArrayList<>();
        this.sales = new ArrayList<>();
        this.debts = new ArrayList<>();
        this.invoices = new ArrayList<>();
        this.companyPayments = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public List<CompanyPayment> getCompanyPayments() {
        return companyPayments;
    }

    public Company addUser(User user) {
        this.users.add(user);
        return this;
    }

    public Company addProduct(Product product) {
        this.products.add(product);
        return this;
    }

    public Company addProvider(Provider provider) {
        this.providers.add(provider);
        return this;
    }

    public Company addCustomer(Customer customer) {
        this.customers.add(customer);
        return this;
    }

    public Company addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        return this;
    }

    public Company addSale(Sale sale) {
        this.sales.add(sale);
        return this;
    }

    public Company addDebt(Debt debt) {
        this.debts.add(debt);
        return this;
    }

    public Company addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        return this;
    }

    public Company addCompanyPayments(CompanyPayment companyPayment) {
        this.companyPayments.add(companyPayment);
        return this;
    }

}

