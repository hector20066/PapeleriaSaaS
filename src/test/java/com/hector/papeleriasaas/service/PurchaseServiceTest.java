package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.persistence.PurchasePersistenceException;
import com.hector.papeleriasaas.service.interfaces.ProductsFacade;
import com.hector.papeleriasaas.service.interfaces.ProvidersFacade;
import com.hector.papeleriasaas.service.interfaces.PurchaseDetailsFacade;
import com.hector.papeleriasaas.service.interfaces.PurchasesFacade;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    private ProvidersFacade providersFacade;

    @Mock
    private ProductsFacade productsFacade;

    @Mock
    private PurchasesFacade purchasesFacade;

    @Mock
    private PurchaseDetailsFacade purchaseDetailsFacade;

    @Mock
    private ConnectionProvider connectionProvider;

    @Mock
    private Connection connection;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    @DisplayName("Testear la conexión")
    void testConnection() throws SQLException, DAOException {
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
    void testRegisterPurchase() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(providersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.empty());
        when(purchasesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class))).thenReturn(5);
        when(productsFacade.findByName(any(Connection.class), anyString())).thenReturn(Optional.empty());
        when(productsFacade.add(any(Connection.class), anyString(), any(BigDecimal.class), any(BigDecimal.class))).thenReturn(5);

        DTOProvider dtoProvider = createDtoProvider();
        DTOProductCreate dtoProduct = createDtoProduct();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), List.of(dtoProduct));

        verify(providersFacade).findById(connection, 10667224);
        verify(providersFacade).add(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(providersFacade, never()).modify(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(purchasesFacade).add(connection, 10667224, new BigDecimal("45000.00"));
        verify(productsFacade).findByName(connection, "Cuaderno Norma");
        verify(productsFacade).add(connection, "Cuaderno Norma", new BigDecimal("4500.00"), new BigDecimal("6000.00"));
        verify(productsFacade, never()).modify(connection, "Cuaderno Norma", 10, new BigDecimal("6000.00"), new BigDecimal("4500.00"), 5);
        verify(purchaseDetailsFacade).add(connection, 5, 5, 10, new BigDecimal("4500.00"));
    }

    @Test
    @DisplayName("Testear el registro de la compra con producto Existente")
    void testRegisterPurchaseProductExist() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(providersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.of(createDtoProvider()));
        when(purchasesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class))).thenReturn(5);
        when(productsFacade.findByName(any(Connection.class), anyString())).thenReturn(Optional.of(createDtoProductDetails()));

        DTOProvider dtoProvider = createDtoProvider();
        DTOProductCreate dtoProduct = createDtoProduct();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), List.of(dtoProduct));

        verify(providersFacade).findById(connection, 10667224);
        verify(providersFacade, never()).add(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(providersFacade).modify(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(purchasesFacade).add(connection, 10667224, new BigDecimal("45000.00"));
        verify(productsFacade).findByName(connection, "Cuaderno Norma");
        verify(productsFacade, never()).add(connection, "Cuaderno Norma", new BigDecimal("4500.00"), new BigDecimal("6000.00"));
        verify(productsFacade).modify(connection, "Cuaderno Norma", 10, new BigDecimal("6000.00"), new BigDecimal("4500.00"), 5);
        verify(purchaseDetailsFacade).add(connection, 5, 5, 10, new BigDecimal("4500.00"));
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
    void testRegisterPurchaseEmptyList() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(providersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.empty());
        when(purchasesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class))).thenReturn(5);

        DTOProvider dtoProvider = createDtoProvider();

        purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), Collections.emptyList());

        verify(connection).setAutoCommit(false);
        verify(connection).commit();
        verify(connection).close();
        verify(connection, never()).rollback();
        verify(connectionProvider).getConnection();

        verify(providersFacade).findById(connection, 10667224);
        verify(providersFacade).add(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(providersFacade, never()).modify(connection, 10667224, "Norma corporation", "Tr 24 16-25", "3137762626", "norma@normacorporation.com");
        verify(purchasesFacade).add(connection, 10667224, new BigDecimal("45000.00"));
        verifyNoInteractions(purchaseDetailsFacade);
        verifyNoInteractions(productsFacade);
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al registrar la compra")
    void testRegisterPurchaseException() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(providersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.empty());
        when(purchasesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class))).thenThrow(new PurchasePersistenceException("Error al agregar compra"));

        DTOProvider dtoProvider = createDtoProvider();

        PurchasePersistenceException ex = assertThrows(
                PurchasePersistenceException.class,
                () -> purchaseService.registerPurchase(dtoProvider, new BigDecimal("45000.00"), Collections.emptyList()),
                () -> "La excepción lanzada no es la esperada"
        );

        assertInstanceOf(DAOException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
        assertEquals("Error al agregar compra", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado");

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