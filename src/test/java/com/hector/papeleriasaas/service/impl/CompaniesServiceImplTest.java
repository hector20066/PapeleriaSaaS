package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.PlansService;
import com.hector.papeleriasaas.service.interfaces.UserService;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompaniesServiceImplTest {

    @Mock
    private DAOCompanies daoCompanies;

    @Mock
    private PlansService plansService;

    @Mock
    private UserService userService;

    @Mock
    private ConnectionProvider connectionProvider;

    @Mock
    private Connection connection;

    @InjectMocks
    private CompaniesServiceImpl companiesService;

    @Test
    @DisplayName("Testear conexión y el manejo de la transacción")
    void testRegisterCompanyConnection() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(plansService.findByName(any(Connection.class), anyString())).thenReturn(new DTOPlanDetails.Builder().id(12345).build());
        when(daoCompanies.add(any(Connection.class), any(DTOCompanyCreate.class))).thenReturn(4);

        companiesService.registerCompanyInfo("Plan Premium", new DTOCompanyCreate.Builder().name("Papelia Saas").phone("3137762626").build(), new DTOUserCreate.Builder().build());

        verify(connectionProvider).getConnection();
        verify(connection).commit();
        verify(connection).setAutoCommit(false);
        verify(connection).close();
        verify(connection, never()).rollback();
    }

    @Test
    @DisplayName("Testear el registro de la empresa y el usuario")
    void testRegisterCompany() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(plansService.findByName(any(Connection.class), anyString())).thenReturn(new DTOPlanDetails.Builder().id(12345).build());
        when(daoCompanies.add(any(Connection.class), any(DTOCompanyCreate.class))).thenReturn(4);

        DTOCompanyCreate dtoCompanyCreate = new DTOCompanyCreate.Builder().name("Papelia Saas").phone("3137762626").build();
        DTOUserCreate dtoUser = new DTOUserCreate.Builder()
                .identification(1066726477)
                .name("Hector")
                .user("hlacevedo")
                .passwd("12345")
                .role(Roles.ROOT)
                .status(Status.ACTIVE)
                .build();

        companiesService.registerCompanyInfo("Plan Premium", dtoCompanyCreate, dtoUser);

        verify(plansService).findByName(connection, "Plan Premium");
        verify(daoCompanies).add(any(Connection.class), any(DTOCompanyCreate.class));
        verify(userService).add(connection, 1066726477, "Hector", "hlacevedo", "12345", Roles.ROOT, Status.ACTIVE, 4);
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción cuando no se encuentra el plan")
    void testRegisterCompanyExceptions() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(plansService.findByName(any(Connection.class), anyString())).thenThrow(new DataNotFoundException("No se ha encontrado el plan, verifique el nombre"));

        DTOCompanyCreate dtoCompanyCreate = new DTOCompanyCreate.Builder().name("Papelia Saas").phone("3137762626").build();
        DTOUserCreate dtoUser = new DTOUserCreate.Builder()
                .identification(1066726477)
                .name("Hector")
                .user("hlacevedo")
                .passwd("12345")
                .role(Roles.ROOT)
                .status(Status.ACTIVE)
                .build();

        DataNotFoundException ex = assertThrows(DataNotFoundException.class,
                () -> companiesService.registerCompanyInfo("Plan Premium", dtoCompanyCreate, dtoUser),
                () -> "La excepción lanzada no es la esperada"
        );

        assertInstanceOf(DAOException.class, ex, () -> "La excepción lanzada no hereda de DAOException");
        assertEquals("No se ha encontrado el plan, verifique el nombre", ex.getMessage());

        verify(connection).rollback();
        verify(connection).close();
        verify(plansService).findByName(connection, "Plan Premium");
        verify(daoCompanies, never()).add(any(Connection.class), any(DTOCompanyCreate.class));
        verify(userService, never()).add(connection, 1066726477, "Hector", "hlacevedo", "12345", Roles.ROOT, Status.ACTIVE, 0);
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear excepción lanzada al registrar la empresa")
    void testRegisterCompanyExceptionAdd() throws SQLException, DAOException {
        when(connectionProvider.getConnection()).thenReturn(connection);
        when(plansService.findByName(any(Connection.class), anyString())).thenReturn(new DTOPlanDetails.Builder().id(12345).build());
        when(daoCompanies.add(any(Connection.class), any(DTOCompanyCreate.class))).thenReturn(0);

        DTOCompanyCreate dtoCompanyCreate = new DTOCompanyCreate.Builder().name("Papelia Saas").phone("3137762626").build();
        DTOUserCreate dtoUser = new DTOUserCreate.Builder()
                .identification(1066726477)
                .name("Hector")
                .user("hlacevedo")
                .passwd("12345")
                .role(Roles.ROOT)
                .status(Status.ACTIVE)
                .build();

        PersistenceException ex = assertThrows(PersistenceException.class,
                () -> companiesService.registerCompanyInfo("Plan Premium", dtoCompanyCreate, dtoUser),
                () -> "La excepción lanzada no es la esperada"
        );

        verify(plansService).findByName(connection, "Plan Premium");
        verify(userService, never()).add(connection, 1066726477, "Hector", "hlacevedo", "12345", Roles.ROOT, Status.ACTIVE, 0);
    }

}