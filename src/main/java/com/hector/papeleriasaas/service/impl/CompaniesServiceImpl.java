package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.database.ConnectionProvider;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dto.company.*;
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

    public CompaniesServiceImpl(DAOCompanies daoCompanies, PlansService plansService, UserService userService, ConnectionProvider connectionProvider) {
        this.daoCompanies = daoCompanies;
        this.plansService = plansService;
        this.userService = userService;
        this.connectionProvider = connectionProvider;
    }

    public void registerCompanyInfo() {
        Connection connection = null;

        try {
            connection = connectionProvider.getConnection();
        } catch (SQLException | DAOException e) {

        }
    }

    @Override
    public int add(Connection connection, Integer nit, String name, String address, String phone, String email, Status status, int planId) throws DAOException {
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

        return daoCompanies.add(dto);
    }

    @Override
    public DTOCompanySimpleDetails findById(int id) throws DAOException {
        Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findById(id);

        if (dtoCompanySimpleDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoCompanySimpleDetailsOptional.get();
    }

    @Override
    public DTOCompanySimpleDetails findByName(String name) throws DAOException {
        Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findByName(name);

        if (dtoCompanySimpleDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoCompanySimpleDetailsOptional.get();
    }

    @Override
    public DTOCompanySimpleDetails findByNit(int nit) throws DAOException {
        Optional<DTOCompanySimpleDetails> dtoCompanySimpleDetailsOptional = daoCompanies.findByNit(nit);

        if (dtoCompanySimpleDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoCompanySimpleDetailsOptional.get();
    }

    @Override
    public DTOCompanyDetails findCompanyInfo(int id) throws DAOException {
        Optional<DTOCompanyDetails> dtoCompanyDetailsOptional = daoCompanies.findCompanyInfo(id);

        if (dtoCompanyDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoCompanyDetailsOptional.get();
    }

    @Override
    public DTOCompanyDetails findCompanyInfo(Connection connection, int id) throws DAOException {
        Optional<DTOCompanyDetails> dtoCompanyDetailsOptional = daoCompanies.findCompanyInfo(connection, id);

        if (dtoCompanyDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return dtoCompanyDetailsOptional.get();
    }

    @Override
    public List<DTOCompanySimpleDetails> findByAll() throws DAOException {
        List<DTOCompanySimpleDetails> list = daoCompanies.findByAll();

        if (list.isEmpty()) {
            throw new DataNotFoundException();
        }

        return list;
    }

    @Override
    public void modify(String name, String address, String phone, String email, int id) throws DAOException {
        DTOCompanyUpdate dto = new DTOCompanyUpdate.Builder()
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .id(id)
                .build();

        daoCompanies.modify(dto);
    }

    @Override
    public void modifyPlan(int planId, int id) throws DAOException {
        DTOCompanyUpdatePlan dto = new DTOCompanyUpdatePlan(id, planId);

        daoCompanies.modifyPlan(dto);
    }

    @Override
    public void modifyStatus(Status status, int id) throws DAOException {
        DTOCompanyUpdateStatus dto = new DTOCompanyUpdateStatus(id, status);

        daoCompanies.modifyStatus(dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoCompanies.delete(id);
    }

}
