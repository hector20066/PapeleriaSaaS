package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.PurchasePersistenceException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOPurchases;
import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseCreate;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.service.interfaces.ProductService;
import com.hector.papeleriasaas.service.interfaces.ProviderProductsService;
import com.hector.papeleriasaas.service.interfaces.ProvidersService;
import com.hector.papeleriasaas.service.interfaces.PurchaseDetailsService;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {

    @Mock
    private DAOPurchases daoPurchases;

    @Mock
    private ProvidersService providersFacade;

    @Mock
    private ProductService productsFacade;

    @Mock
    private PurchaseDetailsService purchaseDetailsService;

    @Mock
    private ProviderProductsService providerProductsService;

    @Mock
    private ConnectionProvider connectionProvider;

    @Mock
    private Connection connection;

    @Mock
    private Session session;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @Test
    @DisplayName("Testear la conexión")
    void testConnection() throws SQLException, ServiceException {
        when(connectionProvider.getConnection()).thenReturn(connection);

        purchaseService.registerPurchase(createDtoProvider(), new BigDecimal("45000.00"), Collections.emptyList());

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
        verify(connection).close();
        verify(connection, never()).rollback();
        verify(connectionProvider).getConnection();
    }

    @Test
    @DisplayName("Testear el registro de la compra")
    void testRegisterPurchase() throws SQLException, DAOException, ServiceException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(daoPurchases.add(any(Connection.class), any(DTOPurchaseCreate.class))).thenReturn(5);
        when(productsFacade.findByName(any(Connection.class), anyString())).thenReturn(null);
        when(productsFacade.add(any(Connection.class), anyString(), any(BigDecimal.class), any(BigDecimal.class))).thenReturn(5);
        when(providersFacade.registerProviderPurchase(any(Connection.class), anyInt(), anyString(), anyString(), anyString(), anyString())).thenReturn(5);

        DTOProvider dtoProvider = createDtoProvider();
        DTOProductCreate dtoProduct = createDtoProduct();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), List.of(dtoProduct));

        verify(daoPurchases).add(any(Connection.class), any(DTOPurchaseCreate.class));
        verify(productsFacade).findByName(connection, "Cuaderno Norma");
        verify(productsFacade).add(connection, "Cuaderno Norma", new BigDecimal("4500.00"), new BigDecimal("6000.00"));
        verify(productsFacade, never()).modify(connection, "Cuaderno Norma", 10, new BigDecimal("6000.00"), new BigDecimal("4500.00"), 5);
        verify(purchaseDetailsService).add(connection, 5, 5, 10, new BigDecimal("4500.00"));
        verify(providerProductsService).add(connection, 5, 5);
    }

    @Test
    @DisplayName("Testear el registro de la compra con producto Existente")
    void testRegisterPurchaseProductExist() throws SQLException, DAOException, ServiceException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(daoPurchases.add(any(Connection.class), any(DTOPurchaseCreate.class))).thenReturn(5);
        when(productsFacade.findByName(any(Connection.class), anyString())).thenReturn(createDtoProductDetails());

        DTOProvider dtoProvider = createDtoProvider();
        DTOProductCreate dtoProduct = createDtoProduct();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), List.of(dtoProduct));

        verify(daoPurchases).add(any(Connection.class), any(DTOPurchaseCreate.class));
        verify(productsFacade).findByName(connection, "Cuaderno Norma");
        verify(productsFacade, never()).add(connection, "Cuaderno Norma", new BigDecimal("4500.00"), new BigDecimal("6000.00"));
        verify(productsFacade).modify(connection, "Cuaderno Norma", 10, new BigDecimal("6000.00"), new BigDecimal("4500.00"), 5);
        verify(purchaseDetailsService).add(connection, 5, 5, 10, new BigDecimal("4500.00"));
        verify(providerProductsService, never()).add(connection, 5, 5);
    }

    private DTOProductDetails createDtoProductDetails() {
        return new DTOProductDetails.Builder()
                .description("Cuaderno Norma")
                .id(5)
                .amount(10)
                .purchasePrice(new BigDecimal("4500.00"))
                .salePrice(new BigDecimal("6000.00"))
                .build();
    }

    private DTOProductCreate createDtoProduct() {
        return new DTOProductCreate.Builder()
                .description("Cuaderno Norma")
                .amount(10)
                .purchasePrice(new BigDecimal("4500.00"))
                .salePrice(new BigDecimal("6000.00"))
                .build();
    }

    @Test
    @DisplayName("Testear el registro de la compra con lista de productos vacía")
    void testRegisterPurchaseEmptyList() throws SQLException, ServiceException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(daoPurchases.add(any(Connection.class), any(DTOPurchaseCreate.class))).thenReturn(5);

        DTOProvider dtoProvider = createDtoProvider();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), Collections.emptyList());

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
        verify(connection).close();
        verify(connection, never()).rollback();
        verify(connectionProvider).getConnection();

        verify(daoPurchases).add(any(Connection.class), any(DTOPurchaseCreate.class));
        verifyNoInteractions(purchaseDetailsService);
        verifyNoInteractions(productsFacade);
        verifyNoInteractions(providerProductsService);
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al registrar la compra")
    void testRegisterPurchaseException() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(daoPurchases.add(any(Connection.class), any())).thenThrow(new PurchasePersistenceException());

        DTOProvider dtoProvider = createDtoProvider();

        ServiceException ex = assertThrows(
                ServiceException.class,
                () -> purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), Collections.emptyList()),
                () -> "La excepción lanzada no es la esperada"
        );

        assertInstanceOf(ServiceException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
        assertEquals("No fue posible registrar la compra.", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado");

        verify(connection).setAutoCommit(false);
        verify(connection).rollback();
        verify(connection).close();
        verify(connection, never()).commit();
        verify(connectionProvider).getConnection();

        verifyNoInteractions(productsFacade);
    }

    private DTOProvider createDtoProvider() {
        return new DTOProvider.Builder()
                .nit(10667224)
                .rSocial("Norma corporation")
                .address("Tr 24 16-25")
                .phone("3137762626")
                .email("norma@normacorporation.com")
                .build();
    }

}