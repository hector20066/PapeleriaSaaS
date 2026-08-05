package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.exception.auth.*;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.remembertokens.DTORememberTokenDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.entities.User;
import com.hector.papeleriasaas.model.enums.Roles;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.CompaniesService;
import com.hector.papeleriasaas.service.interfaces.RememberTokensService;
import com.hector.papeleriasaas.service.interfaces.UserService;
import com.hector.papeleriasaas.utils.UserConfig;

import javax.swing.*;
import java.util.Optional;

public class Login {

    private final UserService userService;
    private final CompaniesService companiesService;
    private final RememberTokensService rememberTokensService;
    private final Session session;

    public Login(UserService userService, CompaniesService companiesService, RememberTokensService rememberTokensService, Session session) {
        this.userService = userService;
        this.companiesService = companiesService;
        this.rememberTokensService = rememberTokensService;
        this.session = session;
    }

    public String login(String user, String passwd, Integer companyNit, int remember) throws AuthException, DAOException {
        String encryptPasswd;
        DTOUserDetails dtoUser;
        DTOCompanyDetails dtoCompany;

        if (companyNit == null || companyNit == 0) {
            Optional<DTOAuthenticationRoot> dtoLoginRootOptional = userService.findAuthenticationRoot(user);

            if (dtoLoginRootOptional.isEmpty()) {
                throw new CredentialsNotFound("No se ha encontrado el administrador con estas credenciales");
            }

            DTOAuthenticationRoot dtoAuthenticationRoot = dtoLoginRootOptional.get();
            encryptPasswd = dtoAuthenticationRoot.getPasswd();

            dtoUser = userService.findById(dtoAuthenticationRoot.getUserId());
            dtoCompany = null;
        } else {
            Optional<DTOAuthentication> dtoLoginOptional = userService.findByNitAndUser(user, companyNit);

            if (dtoLoginOptional.isEmpty()) {
                throw new CredentialsNotFound("No se ha encontrado el usuario con estas credenciales");
            }

            DTOAuthentication dtoAuthentication = dtoLoginOptional.get();
            encryptPasswd = dtoAuthentication.getPasswd();

            if (dtoAuthentication.getCompanyStatus().equals(Status.INACTIVE)) {
                throw new InactiveCompanyException("Su empresa se encuentra inactiva, por favor realize el debido pago");
            }

            dtoUser = userService.findById(dtoAuthentication.getUserId());
            dtoCompany = companiesService.findCompanyInfo(dtoAuthentication.getCompanyId());
        }

        if (!BCryptEncryption.verifyPassword(passwd, encryptPasswd)) {
            throw new IncorrectPasswordException("Contraseña incorrecta");
        }

        Integer companyId = dtoCompany == null ? null : dtoCompany.getId();

        createSession(dtoUser, dtoCompany);

        return remember == JOptionPane.YES_OPTION
                ? rememberTokensService.createToken(dtoUser.getId(), companyId)
                : "";
    }

    public void autoLogin(String token) throws AuthException, DAOException {
        DTORememberTokenDetails dtoToken = rememberTokensService.findByToken(token);
        DTOUserDetails dtoUser = userService.findById(dtoToken.getUserId());
        DTOCompanyDetails dtoCompany = null;

        if (dtoToken.getCompanyId() != null) {
            dtoCompany = companiesService.findCompanyInfo(dtoToken.getCompanyId());
        }

        if (dtoCompany != null && dtoCompany.getStatus() == Status.INACTIVE) {
            throw new InactiveCompanyException("La empresa se encuentra inactiva.");
        }

        createSession(dtoUser, dtoCompany);
    }

    private void createSession(DTOUserDetails dtoUser, DTOCompanyDetails dtoCompany) throws AuthException {
        if (dtoUser.getStatus() == Status.INACTIVE) {
            throw new InactiveUserException("El usuario no está activo.");
        }

        if (dtoUser.getRole().equals(Roles.OTHER)) {
            throw new AuthException("El rol de este usuario no tiene acceso al sistema");
        }

        session.login(dtoUser, dtoCompany);
    }

    public void logout() throws DAOException {
        session.logout();

        String token = UserConfig.get("remember.token");
        rememberTokensService.deleteByToken(token);

        UserConfig.set("remember.enabled", "false");
        UserConfig.set("remember.token", "");
    }

}
