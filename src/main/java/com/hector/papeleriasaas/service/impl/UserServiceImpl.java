package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.*;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.service.ServiceException;
import com.hector.papeleriasaas.exception.service.ServiceDataNotFoundException;
import com.hector.papeleriasaas.exception.service.ServicePersistenceException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.user.*;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.UserService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class UserServiceImpl implements UserService {

    private final DAOUsers daoUsers;
    private final Session session;

    public UserServiceImpl(DAOUsers daoUsers, Session session) {
        this.daoUsers = daoUsers;
        this.session = session;
    }

    @Override
    public int add(Integer identification, String name, String user, String passwd, Roles role, Status status) throws ServiceException {
        try {
            DTOUserCreate dto = new DTOUserCreate.Builder()
                    .identification(identification)
                    .name(name)
                    .user(user)
                    .passwd(encryptPasswd(passwd))
                    .role(role)
                    .status(status)
                    .companyId(session.getCompanyId())
                    .build();

            return daoUsers.add(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int add(Integer identification, String name, String user, String passwd, Roles role, Status status, Integer companyId) throws ServiceException {
        try {
            DTOUserCreate dto = new DTOUserCreate.Builder()
                    .identification(identification)
                    .name(name)
                    .user(user)
                    .passwd(encryptPasswd(passwd))
                    .role(role)
                    .status(status)
                    .companyId(companyId)
                    .build();

            return daoUsers.add(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public int add(Connection connection, Integer identification, String name, String user, String passwd, Roles role, Status status, Integer companyId) throws ServiceException {
        try {
            DTOUserCreate dto = new DTOUserCreate.Builder()
                    .identification(identification)
                    .name(name)
                    .user(user)
                    .passwd(encryptPasswd(passwd))
                    .role(role)
                    .status(status)
                    .companyId(companyId)
                    .build();

            return daoUsers.add(connection, dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    private String encryptPasswd(String plainPasswd) {
        return BCryptEncryption.encryptPassword(plainPasswd);
    }

    @Override
    public DTOUserDetails findByIdentification(Integer identification) throws ServiceException {
        try {
            Optional<DTOUserDetails> userDetailsOptional = daoUsers.findByIdentification(identification);

            if (userDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return userDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOUserDetails findById(Integer id) throws ServiceException {
        try {
            Optional<DTOUserDetails> userDetailsOptional = daoUsers.findById(id);

            if (userDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return userDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public DTOUserDetails findByUser(String user) throws ServiceException {
        try {
            Optional<DTOUserDetails> userDetailsOptional = daoUsers.findByUser(user);

            if (userDetailsOptional.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return userDetailsOptional.get();

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOUserDetails> findByAll() throws ServiceException {
        try {
            List<DTOUserDetails> list = daoUsers.findByAll();

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return list;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public List<DTOUserDetails> findByAllCompany() throws ServiceException {
        try {
            List<DTOUserDetails> list = daoUsers.findByAll(session.getCompanyId());

            if (list.isEmpty()) {
                throw new ServiceDataNotFoundException();
            }

            return list;

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public Optional<DTOAuthentication> findByNitAndUser(String user, int companyNit) throws ServiceException {
        try {
            return daoUsers.findByNitAndUser(user, companyNit);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public Optional<DTOAuthenticationRoot> findAuthenticationRoot(String user) throws ServiceException {
        try {
            return daoUsers.findAuthenticationRoot(user);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modify(int id, int identification, String name, String user, Roles role, Status status) throws ServiceException {
        try {
            DTOUserUpdate dto = new DTOUserUpdate.Builder()
                    .identification(identification)
                    .name(name)
                    .user(user)
                    .roles(role)
                    .status(status)
                    .id(id)
                    .build();

            daoUsers.modify(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modifyPassword(String passwd, int id) throws ServiceException {
        try {
            DTOUserUpdatePasswd dto = new DTOUserUpdatePasswd(passwd, id);

            daoUsers.modifyPassword(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void modifyPasswordCompany(String passwd, int id) throws ServiceException {
        try {
            DTOUserUpdatePasswdCompany dto = new DTOUserUpdatePasswdCompany(passwd, id, session.getCompanyId());

            daoUsers.modifyPasswordCompany(dto);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            daoUsers.delete(id);

        } catch (DAOException e) {
            throw new ServicePersistenceException("No fue posible completar la operación.", e);
        }
    }

}
