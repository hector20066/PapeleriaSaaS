package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.exception.dao.PersistenceException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.*;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.CompaniesService;
import com.hector.papeleriasaas.service.interfaces.PlansService;
import com.hector.papeleriasaas.service.interfaces.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class CompaniesServiceImpl implements CompaniesService {

    private final DAOCompanies daoCompanies;
    private final PlansService plansService;
    private final UserService userService;
    private final ConnectionProvider connectionProvider;

    public CompaniesServiceImpl(DAOCompanies daoCompanies, PlansService plansService, UserService userService,
                                ConnectionProvider connectionProvider) {
        this.daoCompanies = daoCompanies;
        this.plansService = plansService;
        this.userService = userService;
        this.connectionProvider = connectionProvider;
    }

    @Override
    public void registerCompanyInfo(String planName, DTOCompanyCreate dtoCompanyCreate, DTOUserCreate dtoUserCreate) throws ServiceException {
        Connection connection = null;

        try {
            connection = connectionProvider.getConnection();
            connection.setAutoCommit(false);

            DTOPlanDetails plan = plansService.findByName(connection, planName);

            if (plan == null) {
                throw new ServiceDataNotFoundException("No se ha encontrado este plan. Por favor verifique la información");
            }

            int planId = plan.getId();

            dtoCompanyCreate.setPlanId(planId);

            int companyId = add(connection, dtoCompanyCreate.getNit(), dtoCompanyCreate.getName(), dtoCompanyCreate.getAddress(),
                    dtoCompanyCreate.getPhone(), dtoCompanyCreate.getEmail(), dtoCompanyCreate.getStatus(), planId);

            if (companyId == 0) {
                throw new PersistenceException("Ha ocurrido un error en el registro de la empresa");
            }

            userService.add(connection, dtoUserCreate.getIdentification(), dtoUserCreate.getName(), dtoUserCreate.getUser(),
                    dtoUserCreate.getPasswd(), dtoUserCreate.getRole(), dtoUserCreate.getStatus(), companyId);

            connection.commit();
        } catch (SQLException | DAOException e) {
            if (connection != null) {
                rollback(connection);
            }

            throw new ServicePersistenceException("No fue posible registrar la empresa.", e);
        } catch (ServiceException e) {
            rollback(connection);
            throw e;
        } finally {
            if (connection != null) {
                close(connection);
            }
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ignored) {
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }

    private int add(Connection connection, Integer nit, String name, String address, String phone, String email,
                    Status status, int planId) throws DAOException, ServiceException {
        if (name.isBlank()) {
            throw new ServicePersistenceException("El nombre de la empresa es requerido");
        }

        if (phone.isBlank()) {
            throw new ServicePersistenceException("El teléfono es requerido");
        }

        DTOCompanyCreate dto = new DTOCompanyCreate.Builder()
                .nit(nit)
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .registrationDate(LocalDate.now())
                .status(status)
                .planId(planId)
                .build();

        return daoCompanies.add(connection, dto);
    }

    @Override
    public DTOCompanySimpleDetails findById(int id) throws ServiceException {
        try {
            Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findById(id);

            if (dtoCompanySimpleDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoCompanySimpleDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCompanySimpleDetails findByName(String name) throws ServiceException {
        try {
            Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findByName(name);

            if (dtoCompanySimpleDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoCompanySimpleDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCompanySimpleDetails findByNit(int nit) throws ServiceException {
        try {
            Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findByNit(nit);

            if (dtoCompanySimpleDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoCompanySimpleDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCompanyDetails findCompanyInfo(int id) throws ServiceException {
        try {
            Optional<DTOCompanyDetails> dtoCompanyDetailsOptional = daoCompanies.findCompanyInfo(id);

            if (dtoCompanyDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoCompanyDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOCompanyDetails findCompanyInfo(Connection connection, int id) throws ServiceException {
        try {
            Optional<DTOCompanyDetails> dtoCompanyDetailsOptional = daoCompanies.findCompanyInfo(connection, id);

            if (dtoCompanyDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return dtoCompanyDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOCompanySimpleDetails> findByAll() throws ServiceException {
        try {
            List<DTOCompanySimpleDetails> list = daoCompanies.findByAll();

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return list;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(String name, String address, String phone, String email, int id) throws ServiceException {
        try {
            DTOCompanyUpdate dto = new DTOCompanyUpdate.Builder()
                    .name(name)
                    .address(address)
                    .phone(phone)
                    .email(email)
                    .id(id)
                    .build();

            daoCompanies.modify(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modifyPlan(int planId, int id) throws ServiceException {
        try {
            DTOCompanyUpdatePlan dto = new DTOCompanyUpdatePlan(id, planId);

            daoCompanies.modifyPlan(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modifyStatus(Status status, int id) throws ServiceException {
        try {
            DTOCompanyUpdateStatus dto = new DTOCompanyUpdateStatus(id, status);

            daoCompanies.modifyStatus(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoCompanies.delete(id);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
