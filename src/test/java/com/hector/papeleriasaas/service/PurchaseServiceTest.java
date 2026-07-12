package com.hector.papeleriasaas.service;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.facade.interfaces.ProductsFacade;
import com.hector.papeleriasaas.facade.interfaces.ProvidersFacade;
import com.hector.papeleriasaas.facade.interfaces.PurchaseDetailsFacade;
import com.hector.papeleriasaas.facade.interfaces.PurchasesFacade;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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
    void testRegisterPurchase() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(providersFacade.findById(any(Connection.class), anyInt())).thenReturn(Optional.empty());
        when(purchasesFacade.add(any(Connection.class), anyInt(), any(BigDecimal.class))).thenReturn(5);
        when(productsFacade.findByName(any(Connection.class), anyString())).thenReturn(Optional.empty());
        when(productsFacade.add(any(Connection.class), anyString(), any(BigDecimal.class), any(BigDecimal.class)));

    }

}