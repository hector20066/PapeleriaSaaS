package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.CustomerNotFoundException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOSales;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.service.impl.SaleServiceImpl;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import com.hector.papeleriasaas.model.dto.customer.DTOCustomerDetails;
import com.hector.papeleriasaas.model.dto.invoice.DTOInvoiceCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.service.interfaces.*;
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
class SaleServiceImplTest {

    @Mock
    private DAOSales daoSales;

    @Mock
    private CustomerService customersFacade;

    @Mock
    private SaleService salesFacade;

    @Mock
    private SaleDetailsService saleDetailsFacade;

    @Mock
    private InvoicesService invoicesFacade;

    @Mock
    private ProductService productsFacade;

    @Mock
    private ConnectionProvider provider;

    @Mock
    private Connection connection;

    @Mock
    private Session session;

    @InjectMocks
    private SaleServiceImpl saleService;

    @Test
    @DisplayName("Testear la conexión y la transacción")
    void testConnection() throws SQLException, ServiceException {
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
    @DisplayName("Testear el registro de la venta")
    void testRegisterSale() throws SQLException, DAOException, ServiceException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(null);

        DTOSaleDetailsCreate dto = createSaleDTO();

        int invoiceNumber = saleService.registerSale(1066726477, "Hector Acevedo",
                "3137762626", new BigDecimal("25000.00"), new BigDecimal("25000.00"), List.of(createSaleDTO()));

        assertEquals(5, invoiceNumber, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).add(connection, 1066726477, "Hector Acevedo", "3137762626");
        verify(customersFacade, never()).modify(connection, "3137762626", 1066726477);
        verify(daoSales).add(any(Connection.class), any(DTOSaleCreate.class));
        verify(saleDetailsFacade).add(connection, invoiceNumber, dto.getProductId(), dto.getAmount(), dto.getUnitPrice());
        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));
    }

    @Test
    @DisplayName("Testear el registro de la venta con cliente existente")
    void testRegisterSaleCustomerExist() throws SQLException, DAOException, ServiceException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(new DTOCustomerDetails.Builder().build());
        DTOSaleDetailsCreate dto = createSaleDTO();

        int invoiceNumber = saleService.registerSale(1066726477, "Hector Acevedo", "3137762626",
                new BigDecimal("25000.00"), new BigDecimal("25000.00"), List.of(createSaleDTO()));

        assertEquals(5, invoiceNumber, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).modify(connection, "3137762626", 1066726477);
        verify(customersFacade, never()).add(connection, 1066726477, "Hector", "3137762626");
        verify(daoSales).add(any(Connection.class), any(DTOSaleCreate.class));
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
    @DisplayName("Testear el registro de la venta sin cliente")
    void testRegisterSaleCustomerNull() throws SQLException,DAOException, ServiceException {
        configureSuccessfulSale();

        int numSale = saleService.registerSale(null, null, null,
                new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList());

        assertEquals(5, numSale, () -> "El numero de la venta no es el esperado");

        verify(daoSales).add(any(Connection.class), any(DTOSaleCreate.class));
        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));

        verifyNoInteractions(customersFacade);
        verifyNoInteractions(saleDetailsFacade);
    }

    @Test
    @DisplayName("Testear el registro de la venta con lista de productos vacía")
    void testRegisterSaleEmptyList() throws SQLException, DAOException, ServiceException {
        configureSuccessfulSale();
        when(customersFacade.findById(any(Connection.class), anyInt())).thenReturn(new DTOCustomerDetails.Builder().build());

        int numSale = saleService.registerSale(1066726477, "Hector Acevedo",
                "3137762626", new BigDecimal("25000.00"), new BigDecimal("25000.00"), Collections.emptyList());

        assertEquals(5, numSale, () -> "El numero de la venta no es el esperado");

        verify(customersFacade).findById(connection, 1066726477);
        verify(customersFacade).modify(connection, "3137762626", 1066726477);
        verify(customersFacade, never()).add(connection, 1066726477, "Hector Acevedo", "3137762626");
        verify(daoSales).add(any(Connection.class), any(DTOSaleCreate.class));

        verify(saleDetailsFacade, never()).add(any(Connection.class), anyInt(), anyInt(), anyInt(), any(BigDecimal.class));
        verifyNoInteractions(saleDetailsFacade);

        verify(invoicesFacade).add(any(Connection.class), any(DTOInvoiceCreate.class));
    }

    private void configureSuccessfulSale() throws SQLException, DAOException, ServiceException {
        when(provider.getConnection()).thenReturn(connection);
        when(daoSales.add(any(Connection.class), any())).thenReturn(5);
        when(invoicesFacade.add(any(Connection.class), any(DTOInvoiceCreate.class))).thenReturn(5);
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al registrar la venta")
    void testRegisterSaleException() throws SQLException, ServiceException {
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
    @DisplayName("Testear verificación de stock bajo")
    void testCheckLowStock() throws ServiceException {
        when(productsFacade.findByAll()).thenReturn(Arrays.asList(
                new DTOProductDetails.Builder().amount(3).description("Cuaderno").build(),
                new DTOProductDetails.Builder().amount(5).description("Lapicero").build(),
                new DTOProductDetails.Builder().amount(1).description("Colores").build())
        );

        String lowStock = saleService.checkLowStock();

        assertFalse(lowStock.isBlank(), () -> "La lista de productos esta vacía");
        assertEquals("Cuaderno, Colores", lowStock, () -> "El resultado no es el esperado");
        verify(productsFacade).findByAll();
    }

    @Test
    @DisplayName("Testear verificación de stock bajo sin productos")
    void testCheckLowStockEmpty() throws ServiceException {
        when(productsFacade.findByAll()).thenReturn(Collections.emptyList());

        String lowStock = saleService.checkLowStock();

        assertTrue(lowStock.isBlank(), () -> "La lista no esta vacía");
        verify(productsFacade).findByAll();
    }

}