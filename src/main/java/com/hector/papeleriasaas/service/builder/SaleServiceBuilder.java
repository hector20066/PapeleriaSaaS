package com.hector.papeleriasaas.service.builder;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.database.ConnectionProviderImpl;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.service.SaleService;

public class SaleServiceBuilder {

    private CustomersFacade customerFacade;
    private SalesFacade saleFacade;
    private SaleDetailsFacade saleDetailsFacade;
    private InvoicesFacade invoiceFacade;
    private ProductsFacade productsFacade;
    private ConnectionProvider connectionProvider;

    public SaleServiceBuilder withCustomerFacade(CustomersFacade customersFacade) {
        this.customerFacade = customersFacade;
        return this;
    }

    public SaleServiceBuilder withSaleFacade(SalesFacade saleFacade) {
        this.saleFacade = saleFacade;
        return this;
    }

    public SaleServiceBuilder withSaleDetailsFacade(SaleDetailsFacade saleDetailsFacade) {
        this.saleDetailsFacade = saleDetailsFacade;
        return this;
    }

    public SaleServiceBuilder withInvoiceFacade(InvoicesFacade invoiceFacade) {
        this.invoiceFacade = invoiceFacade;
        return this;
    }

    public SaleServiceBuilder withProductsFacade(ProductsFacade productsFacade) {
        this.productsFacade = productsFacade;
        return this;
    }

    public SaleServiceBuilder withConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        return this;
    }

}
