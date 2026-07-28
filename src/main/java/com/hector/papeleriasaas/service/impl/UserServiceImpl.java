package com.hector.papeleriasaas.service.impl;

import com.hector.papeleriasaas.auth.DTOAuthentication;
import com.hector.papeleriasaas.auth.DTOAuthenticationRoot;
import com.hector.papeleriasaas.auth.Session;
import com.hector.papeleriasaas.auth.SessionImpl;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.user.*;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.UserService;

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
    public int add(Integer identification, String name, String user, String passwd, Roles role, Status status) throws DAOException {
        DTOUserCreate dto = new DTOUserCreate.Builder()
                .identification(identification)
                .name(name)
                .user(user)
                .passwd(passwd)
                .role(role)
                .status(status)
                .companyId(session.getCompanyId())
                .build();

        return daoUsers.add(dto);
    }

    @Override
    public DTOUserDetails findByIdentification(Integer identification) throws DAOException {
        Optional<DTOUserDetails> userDetailsOptional = daoUsers.findByIdentification(identification);

        if (userDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return userDetailsOptional.get();
    }

    @Override
    public DTOUserDetails findById(Integer id) throws DAOException {
        Optional<DTOUserDetails> userDetailsOptional = daoUsers.findById(id);

        if (userDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return userDetailsOptional.get();
    }

    @Override
    public DTOUserDetails findByUser(String user) throws DAOException {
        Optional<DTOUserDetails> userDetailsOptional = daoUsers.findByUser(user);

        if (userDetailsOptional.isEmpty()) {
            throw new DataNotFoundException();
        }

        return userDetailsOptional.get();
    }

    @Override
    public List<DTOUserDetails> findByAll() throws DAOException {
        List<DTOUserDetails> list = daoUsers.findByAll();

        if (list.isEmpty()) {
            throw new DataNotFoundException();
        }

        return list;
    }

    @Override
    public List<DTOUserDetails> findByAllCompany() throws DAOException {
        List<DTOUserDetails> list = daoUsers.findByAll(session.getCompanyId());

        if (list.isEmpty()) {
            throw new DataNotFoundException();
        }

        return list;
    }

    @Override
    public Optional<DTOAuthentication> findByNitAndUser(String user, int companyNit) throws DAOException {
        return daoUsers.findByNitAndUser(user, companyNit);
    }

    @Override
    public Optional<DTOAuthenticationRoot> findAuthenticationRoot(String user) throws DAOException {
        return daoUsers.findAuthenticationRoot(user);
    }

    @Override
    public void modify(int id, int identification, String name, String user, Roles role, Status status) throws DAOException {
        DTOUserUpdate dto = new DTOUserUpdate.Builder()
                .identification(identification)
                .name(name)
                .user(user)
                .roles(role)
                .status(status)
                .id(id)
                .build();

        daoUsers.modify(dto);
    }

    @Override
    public void modifyPassword(String passwd, int id) throws DAOException {
        DTOUserUpdatePasswd dto = new DTOUserUpdatePasswd(passwd, id);

        daoUsers.modifyPassword(dto);
    }

    @Override
    public void modifyPasswordCompany(String passwd, int id) throws DAOException {
        DTOUserUpdatePasswdCompany dto = new DTOUserUpdatePasswdCompany(passwd, id, session.getCompanyId());

        daoUsers.modifyPasswordCompany(dto);
    }

    @Override
    public void delete(int id) throws DAOException {
        daoUsers.delete(id);
    }

}
