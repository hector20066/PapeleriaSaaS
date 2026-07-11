package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    private CustomersFacade customersFacade;
    private SalesFacade salesFacade;
    private SaleDetailsFacade saleDetailsFacade;
    private InvoicesFacade invoicesFacade;
    private ProductsFacade productsFacade;
    ConnectionProvider provider;
    Connection connection;

    private SaleService saleService;

    @BeforeEach
    void setUp() {
        customersFacade = mock(CustomersFacade.class);
        salesFacade = mock(SalesFacade.class);
        saleDetailsFacade = mock(SaleDetailsFacade.class);
        invoicesFacade = mock(InvoicesFacade.class);
        productsFacade = mock(ProductsFacade.class);
        provider = mock(ConnectionProvider.class);
        connection = mock(Connection.class);

        saleService = new SaleService(customersFacade, salesFacade, saleDetailsFacade, invoicesFacade, productsFacade, provider);
    }

    @Test
    void testRegisterSale() throws DAOException, SQLException {
        when(provider.getConnection()).thenReturn(connection);
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.of(new DTOCustomerDetails.Builder()
                .identification(1066726477)
                .phone("3137762626")
                .name("hector")
                .build()));
        when(salesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class), any(BigDecimal.class))).thenReturn(5);
        when(invoicesFacade.add(any(Connection.class), any(DTOInvoiceCreate.class))).thenReturn(5);

        int result = saleService.registerSale(1066726477, "Hector Acevedo", "3137762626", new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList());

        assertEquals(5, result, () -> "El resultado no es el esperado");
    }

    @Test
    void testCheckLowStock() throws DAOException {
        when(productsFacade.findByAll()).thenReturn(Arrays.asList(new DTOProductDetails.Builder().amount(3).description("Cuaderno").build(),
                new DTOProductDetails.Builder().amount(5).description("Lapicero").build(),
                new DTOProductDetails.Builder().amount(1).description("Colores").build()));

        String lowStock = saleService.checkLowStock();

        assertEquals("Cuaderno, Colores", lowStock);
    }

}