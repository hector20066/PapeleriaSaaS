package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.CustomerNotFoundException;
import com.hector.papeleriasaas.facade.interfaces.*;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @Mock
    private CustomersFacade customersFacade;

    @Mock
    private SalesFacade salesFacade;

    @Mock
    private SaleDetailsFacade saleDetailsFacade;

    @Mock
    private InvoicesFacade invoicesFacade;

    @Mock
    private ProductsFacade productsFacade;

    @Mock
    private ConnectionProvider provider;

    @Mock
    private Connection connection;

    @InjectMocks
    private SaleService saleService;

    @Test
    @DisplayName("Testear la conexión y la transacción")
    void testConnection() throws DAOException, SQLException {
        when(provider.getConnection()).thenReturn(connection);

        saleService.registerSale(1066726477, "Hector Acevedo", "3137762626",
                new BigDecimal("25000.00"), new BigDecimal("25000.00"), List.of(createSaleDTO()));

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
        verify(connection).close();
        verify(connection, never()).rollback();
        verify(provider).getConnection();
    }

    @Test
    void testRegisterSale() throws DAOException, SQLException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.empty());

        DTOSaleDetailsCreate dto = createSaleDTO();

        int invoiceNumber = saleService.registerSale(1066726477, "Hector Acevedo",
                "3137762626", new BigDecimal("25000.00"), new BigDecimal("25000.00"), List.of(createSaleDTO()));

        assertEquals(5, invoiceNumber, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).add(connection, 1066726477, "Hector Acevedo", "3137762626");
        verify(customersFacade, never()).modify(connection, "3137762626", 1066726477);
        verify(salesFacade).add(connection, 1066726477, new BigDecimal("25000.00"), new BigDecimal("25000.00"));
        verify(saleDetailsFacade).add(connection, invoiceNumber, dto.getProductId(), dto.getAmount(), dto.getUnitPrice());
        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));
    }

    @Test
    void testRegisterSaleCustomerExist() throws DAOException, SQLException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.of(new DTOCustomerDetails.Builder().build()));
        DTOSaleDetailsCreate dto = createSaleDTO();

        int invoiceNumber = saleService.registerSale(1066726477, "Hector Acevedo", "3137762626",
                new BigDecimal("25000.00"), new BigDecimal("25000.00"), List.of(createSaleDTO()));

        assertEquals(5, invoiceNumber, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).modify(connection, "3137762626", 1066726477);
        verify(customersFacade, never()).add(connection, 1066726477, "Hector", "3137762626");
        verify(salesFacade).add(connection, 1066726477, new BigDecimal("25000.00"), new BigDecimal("25000.00"));
        verify(saleDetailsFacade).add(connection, invoiceNumber, dto.getProductId(), dto.getAmount(), dto.getUnitPrice());
        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));
    }

    private DTOSaleDetailsCreate createSaleDTO() {
        DTOSaleDetailsCreate dto = new DTOSaleDetailsCreate();
        dto.setProductId(5);
        dto.setAmount(5);
        dto.setUnitPrice(new BigDecimal("25000.00"));

        return dto;
    }

    @Test
    void testRegisterSaleCustomerNull() throws DAOException, SQLException {
        configureSuccessfulSale();

        int numSale = saleService.registerSale(null, null, null,
                new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList());

        assertEquals(5, numSale, () -> "El numero de la venta no es el esperado");

        verify(salesFacade).add(connection, null, new BigDecimal("25000.00"), new BigDecimal("25000.00"));
        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));

        verifyNoInteractions(customersFacade);
        verifyNoInteractions(saleDetailsFacade);
    }

    @Test
    void testRegisterSaleEmptyList() throws DAOException, SQLException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.of(new DTOCustomerDetails.Builder().build()));

        int numSale = saleService.registerSale(1066726477, "Hector Acevedo",
                "3137762626", new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList());

        assertEquals(5, numSale, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).modify(connection, "3137762626", 1066726477);
        verify(customersFacade, never()).add(connection, 1066726477, "Hector Acevedo", "3137762626");
        verify(salesFacade).add(connection, 1066726477, new BigDecimal("25000.00"), new BigDecimal("25000.00"));

        verify(saleDetailsFacade, never()).add(any(Connection.class), anyInt(), anyInt(), anyInt(), any(BigDecimal.class));
        verifyNoInteractions(saleDetailsFacade);

        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));
    }

    private void configureSuccessfulSale() throws SQLException, DAOException {
        when(provider.getConnection()).thenReturn(connection);
        when(salesFacade.add(any(Connection.class), any(), any(BigDecimal.class), any(BigDecimal.class))).thenReturn(5);
        when(invoicesFacade.add(any(Connection.class), any(DTOInvoiceCreate.class))).thenReturn(5);
    }

    @Test
    @Tag("exception")
    void testRegisterSaleException() throws DAOException, SQLException {
        when(provider.getConnection()).thenReturn(connection);
        when(customersFacade.findById(any(Connection.class), anyInt())).thenThrow(new CustomerNotFoundException("1066726477"));

        CustomerNotFoundException ex = assertThrows(
                CustomerNotFoundException.class,
                () -> saleService.registerSale(1066726477, "Hector Acevedo", "3137762626",
                        new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList()),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Cliente no encontrado con parámetro: '1066726477'", ex.getMessage(), () -> "El mensaje de error no es el esperado");
        assertInstanceOf(DAOException.class, ex, () -> "La instancia no hereda de la Excepción esperada");

        verify(connection).setAutoCommit(false);
        verify(connection).rollback();
        verify(connection).close();
        verify(connection, never()).commit();
        verify(provider).getConnection();

        verifyNoInteractions(salesFacade);
        verifyNoInteractions(saleDetailsFacade);
        verifyNoInteractions(invoicesFacade);
        verifyNoInteractions(productsFacade);
    }

    @Test
    void testCheckLowStock() throws DAOException {
        when(productsFacade.findByAll()).thenReturn(Arrays.asList(
                new DTOProductDetails.Builder().amount(3).description("Cuaderno").build(),
                new DTOProductDetails.Builder().amount(5).description("Lapicero").build(),
                new DTOProductDetails.Builder().amount(1).description("Colores").build())
        );

        String lowStock = saleService.checkLowStock();
        assertEquals("Cuaderno, Colores", lowStock, () -> "El resultado no es el esperado");
        verify(productsFacade).findByAll();
    }

}