package com.hector.papeleriasaas.controller;

import com.hector.papeleriasaas.auth.Login;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.controller.admin.ControllerAdminMenu;
import com.hector.papeleriasaas.controller.admin.ControllerRegisterUsers;
import com.hector.papeleriasaas.controller.admin.ControllerUserManagement;
import com.hector.papeleriasaas.controller.builder.*;
import com.hector.papeleriasaas.controller.company.ControllerCompanyView;
import com.hector.papeleriasaas.controller.company.ControllerCustomer;
import com.hector.papeleriasaas.controller.company.ControllerPurchasingReport;
import com.hector.papeleriasaas.controller.company.ControllerSuppliers;
import com.hector.papeleriasaas.controller.login.ControllerCreateAccount;
import com.hector.papeleriasaas.controller.login.ControllerLogin;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.database.ConnectionProviderImpl;
import com.hector.papeleriasaas.database.DataBasePool;
import com.hector.papeleriasaas.exception.auth.AuthException;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dao.impl.*;
import com.hector.papeleriasaas.model.dao.interfaces.*;
import com.hector.papeleriasaas.observer.ConcreteSubject;
import com.hector.papeleriasaas.service.impl.*;
import com.hector.papeleriasaas.service.interfaces.*;
import com.hector.papeleriasaas.utils.NavigatorUtils;
import com.hector.papeleriasaas.utils.UserConfig;
import com.hector.papeleriasaas.view.content.ContentView;
import com.hector.papeleriasaas.view.content.IndexView;
import com.hector.papeleriasaas.view.content.panels.admin.*;
import com.hector.papeleriasaas.view.content.panels.companies.*;
import com.hector.papeleriasaas.view.content.panels.login.CreateAccountView;
import com.hector.papeleriasaas.view.content.panels.login.LoginView;
import com.hector.papeleriasaas.view.content.panels.login.SelectPlansView;

import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Coordinator {

    // Observer
    private ConcreteSubject subject;

    private final Session session = new SessionImpl();

    private ContentView contentView;
    private IndexView index;

    // Login Views
    private CreateAccountView createAccount;
    private LoginView loginView;
    private SelectPlansView selectPlans;

    // Companies Views
    private CompaniesMenu companiesMenu;
    private CompanyView companyView;
    private CustomersView customers;
    private DashboardCompanyView dashboardCompany;
    private DebtReportView debtReport;
    private InventoryView inventory;
    private ModifyProductView modifyProduct;
    private ProvidersView providers;
    private PurchaseProductsView purchaseProducts;
    private PurchasingReportView purchasingReport;
    private SalesManagementView salesManagement;
    private SalesReportView salesReport;

    // Admin Views
    private AdminMenu adminMenu;
    private BusinessManagementView businessManagement;
    private DashboardAdminView dashboardAdmin;
    private ManagePlansView managePlans;
    private PaymentHistoryView paymentHistory;
    private UserManagementView userManagement;
    private RegisterUsersView registerUsersView;

    // Connection
    private final DataBasePool dataBase = new DataBasePool();
    private final ConnectionProvider connectionProvider = new ConnectionProviderImpl(dataBase);

    // DAO's
    private final DAOCompanies daoCompanies = new DAOCompaniesImpl(connectionProvider);
    private final DAOCompanyPayments daoCompanyPayments = new DAOCompanyPaymentsImpl(connectionProvider);
    private final DAOCustomers daoCustomers = new DAOCustomersImpl(connectionProvider);
    private final DAODebts daoDebts = new DAODebtsImpl(connectionProvider);
    private final DAOInvoices daoInvoices = new DAOInvoicesImpl();
    private final DAOPlans daoPlans = new DAOPlansImpl(connectionProvider);
    private final DAOProducts daoProducts = new DAOProductsImpl(connectionProvider);
    private final DAOProviders daoProviders = new DAOProvidersImpl(connectionProvider);
    private final DAOPurchases daoPurchases = new DAOPurchasesImpl(connectionProvider);
    private final DAOPurchaseDetails daoPurchaseDetails = new DAOPurchaseDetailsImpl();
    private final DAOSales daoSales = new DAOSalesImpl(connectionProvider);
    private final DAOSaleDetails daoSaleDetails = new DAOSaleDetailsImpl();
    private final DAOUsers daoUsers = new DAOUsersImpl(connectionProvider);
    private final DAOProviderProducts daoProviderProducts = new DAOProviderProductsImpl(connectionProvider);
    private final DAORememberTokens daoRememberTokens = new DAORememberTokensImpl(connectionProvider);

    // Services
    private final PlansService plansService = new PlansServiceImpl(daoPlans);
    private final UserService userService = new UserServiceImpl(daoUsers, session);
    private final CompaniesService companiesService = new CompaniesServiceImpl(daoCompanies, plansService, userService, connectionProvider);
    private final CompanyPaymentsService companyPaymentsService = new CompanyPaymentsServiceImpl(daoCompanyPayments);
    private final CustomerService customerService = new CustomerServiceImpl(daoCustomers, session);
    private final DebtsService debtsService = new DebtsServiceImpl(daoDebts, session);
    private final InvoicesService invoicesService = new InvoicesServiceImpl(daoInvoices);
    private final ProductService productService = new ProductServiceImpl(daoProducts, session);
    private final ProvidersService providersService = new ProvidersServiceImpl(daoProviders, session);
    private final PurchaseDetailsService purchaseDetailsService = new PurchaseDetailsServiceImpl(daoPurchaseDetails);
    private final ProviderProductsService providerProductsService = new ProviderProductsServiceImpl(daoProviderProducts, session);
    private final PurchaseService purchaseService = new PurchaseServiceImpl(daoPurchases, providersService, productService, purchaseDetailsService, providerProductsService, connectionProvider, session);
    private final SaleDetailsService saleDetailsService = new SaleDetailsServiceImpl(daoSaleDetails);
    private final SaleService saleService = new SaleServiceImpl(daoSales, customerService, saleDetailsService, invoicesService, productService, connectionProvider, session);
    private final RememberTokensService rememberTokensService = new RememberTokensServiceImpl(daoRememberTokens);

    private final Login login = new Login(userService, companiesService, rememberTokensService, session);

    public void startApp() {
        subject = ConcreteSubject.getInstance();
        startViews();
        startDrivers();

        try {
            boolean rememberEnabled = Boolean.parseBoolean(UserConfig.get("remember.enabled"));
            String token = UserConfig.get("remember.token");

            if (rememberEnabled && !token.isBlank()) {
                login.autoLogin(token);
            }

            EventQueue.invokeLater(() -> {
                contentView.setVisible(true);
                contentView.setLocationRelativeTo(null);

                if (rememberEnabled && !token.isBlank()) {
                    Integer companyId = session.getCompanyId();

                    if (companyId == null || companyId == 0) {
                        startAdminEnvironment();
                    } else {
                        startCompaniesEnvironment();
                    }
                } else {
                    NavigatorUtils.showPanels(ContentView.getJpl_content(), index);
                }
            });
        } catch (AuthException | DAOException e) {
            e.printStackTrace();
        }
    }

    private void startViews() {
        this.contentView = new ContentView();
        this.index = new IndexView();
        this.loginView = new LoginView();
        this.createAccount = new CreateAccountView();
        this.selectPlans = new SelectPlansView();
    }

    private void startDrivers() {
        new ControllerLogin(loginView, createAccount, this, login, session);
        new ControllerIndex(index, loginView, createAccount);
        new ControllerCreateAccount(createAccount, index, loginView, companiesService, plansService);
    }

    public void startCompaniesEnvironment() {
        startCompaniesViews();
        startCompaniesDrivers();
        loadData();

        NavigatorUtils.showPanels(ContentView.getJpl_content(), companiesMenu);
        NavigatorUtils.showPanels(CompaniesMenu.getJpl_content(), dashboardCompany);
    }

    private void startCompaniesViews() {
        this.companiesMenu = new CompaniesMenu();
        this.companyView = new CompanyView();
        this.customers = new CustomersView();
        this.dashboardCompany = new DashboardCompanyView();
        this.debtReport = new DebtReportView();
        this.inventory = new InventoryView();
        this.modifyProduct = new ModifyProductView();
        this.providers = new ProvidersView();
        this.purchaseProducts = new PurchaseProductsView();
        this.purchasingReport = new PurchasingReportView();
        this.salesManagement = new SalesManagementView();
        this.salesReport = new SalesReportView();
    }

    private void startCompaniesDrivers() {
        new ControllerContentBuilder()
                .withViewContent(companiesMenu)
                .withViewDashboard(dashboardCompany)
                .withViewPurchaseProducts(purchaseProducts)
                .withViewSalesManagement(salesManagement)
                .withViewSalesReport(salesReport)
                .withViewPurchasingReport(purchasingReport)
                .withViewDebtReport(debtReport)
                .withViewCustomers(customers)
                .withViewSuppliers(providers)
                .withCoordinator(this)
                .withViewIndex(index)
                .withCompanyView(companyView)
                .withLogin(login)
                .withSession(session)
                .build();

        new ControllerInventoryBuilder()
                .withViewInventory(inventory)
                .withViewPurchaseProducts(purchaseProducts)
                .withViewModifyProducts(modifyProduct)
                .withSubject(subject)
                .withProductService(productService)
                .build();

        new ControllerDashboardBuilder()
                .withViewDashboard(dashboardCompany)
                .withSubject(subject)
                .withProductService(productService)
                .withSaleService(saleService)
                .build();

        new ControllerDebtReportBuilder()
                .withViewDebtReport(debtReport)
                .withSubject(subject)
                .withDebtsService(debtsService)
                .withSaleService(saleService)
                .build();

        new ControllerModifyProductsBuilder()
                .withViewModifyProduct(modifyProduct)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withProductService(productService)
                .build();

        new ControllerPurchaseProductsBuilder()
                .withViewProductManagement(purchaseProducts)
                .withViewInventory(inventory)
                .withSubject(subject)
                .withPurchaseService(purchaseService)
                .withProductService(productService)
                .build();

        new ControllerSalesManagementBuilder()
                .withViewSalesManagement(salesManagement)
                .withSubject(subject)
                .withProductService(productService)
                .withSaleService(saleService)
                .build();

        new ControllerSalesReportBuilder()
                .withViewSalesReport(salesReport)
                .withSubject(subject)
                .withSaleService(saleService)
                .withProductService(productService)
                .build();

        new ControllerPurchasingReport(purchasingReport, subject, purchaseService);
        new ControllerCustomer(customers, subject, customerService);
        new ControllerSuppliers(providers, subject, providersService);
        new ControllerCompanyView(companyView, session, subject, userService, plansService, companyPaymentsService);
    }

    public void startAdminEnvironment() {
        startAdminViews();
        startAdminDrivers();
        loadData();

        NavigatorUtils.showPanels(ContentView.getJpl_content(), adminMenu);
        NavigatorUtils.showPanels(AdminMenu.getJpl_content(), dashboardAdmin);
    }

    private void startAdminViews() {
        this.adminMenu = new AdminMenu();
        this.businessManagement = new BusinessManagementView();
        this.dashboardAdmin = new DashboardAdminView();
        this.managePlans = new ManagePlansView();
        this.paymentHistory = new PaymentHistoryView();
        this.userManagement = new UserManagementView();
        this.registerUsersView = new RegisterUsersView();
    }

    private void startAdminDrivers() {
        new ControllerAdminMenu(adminMenu, businessManagement, dashboardAdmin, managePlans, paymentHistory, userManagement, index, session, this, login);
        new ControllerRegisterUsers(registerUsersView, subject, userService);
        new ControllerUserManagement(userManagement, registerUsersView);
    }

    public void logoutAdmin() {
        this.adminMenu = null;
        this.businessManagement = null;
        this.dashboardAdmin = null;
        this.managePlans = null;
        this.paymentHistory = null;
        this.userManagement = null;
        this.registerUsersView = null;
    }

    public void logoutCompany() {
        this.companiesMenu = null;
        this.companyView = null;
        this.customers = null;
        this.dashboardCompany = null;
        this.debtReport = null;
        this.inventory = null;
        this.modifyProduct = null;
        this.providers = null;
        this.purchaseProducts = null;
        this.purchasingReport = null;
        this.salesManagement = null;
        this.salesReport = null;
    }

    public void loadData() {
        subject.notifyObservers();
    }

}
