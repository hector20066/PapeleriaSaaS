package com.hector.papeleriasaas.model.dao.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DAOProductsImplTest {

    @Mock
    private ConnectionProvider connectionProvider;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks()
    private DAOProductsImpl daoProducts;

    @Test
    void testAddMethod() throws SQLException, DAOException {
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(3);

        int id = daoProducts.add(connection, new DTOProductCreate.Builder()
                .description("Cuaderno Norma")
                .purchasePrice(new BigDecimal("25000"))
                .salePrice(new BigDecimal("50000"))
                .companyId(5)
                .build());

        assertEquals(3, id);

        verify(connection).prepareStatement(any(), anyInt());
        verify(statement).executeUpdate();
        verify(statement).getGeneratedKeys();
        verify(statement).setString(anyInt(), anyString());
        verify(statement).setBigDecimal(2, new BigDecimal("25000"));
        verify(statement).setInt(anyInt(), anyInt());
        verify(resultSet).next();
        verify(resultSet).getInt(1);
    }

    @Test
    void testFindByIdOptionalEmpty() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        Optional<DTOProductDetails> optional = daoProducts.findById(2, 1);

        assertTrue(optional.isEmpty());
        verify(connectionProvider).getConnection();
        verify(connection).prepareStatement(anyString());
        verify(statement).setInt(1, 2);
        verify(statement).executeQuery();
        verify(resultSet).next();
    }

}