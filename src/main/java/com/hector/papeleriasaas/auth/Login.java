package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.exception.auth.AuthException;
import com.hector.papeleriasaas.exception.auth.CredentialsNotFound;
import com.hector.papeleriasaas.exception.auth.InactiveCompanyException;
import com.hector.papeleriasaas.exception.auth.IncorrectPasswordException;
import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.enums.Status;
import com.hector.papeleriasaas.service.interfaces.CompaniesService;
import com.hector.papeleriasaas.service.interfaces.UserService;

import java.util.Optional;

public class Login {

    private final UserService userService;
    private final CompaniesService companiesService;

    public Login(UserService userService, CompaniesService companiesService) {
        this.userService = userService;
        this.companiesService = companiesService;
    }

    public void login(String user, String passwd, Integer companyNit) throws AuthException, DAOException {
        String encryptPasswd;
        Status userStatus;
        DTOUserDetails dtoUser;
        DTOCompanyDetails dtoCompany;

        if (companyNit == null || companyNit == 0) {
            Optional<DTOAuthenticationRoot> dtoLoginRootOptional = userService.findAuthenticationRoot(user);

            if (dtoLoginRootOptional.isEmpty()) {
                throw new CredentialsNotFound();
            }

            DTOAuthenticationRoot dtoAuthenticationRoot = dtoLoginRootOptional.get();
            encryptPasswd = dtoAuthenticationRoot.getPasswd();
            userStatus = dtoAuthenticationRoot.getStatus();

            dtoUser = userService.findById(dtoAuthenticationRoot.getUserId());
            dtoCompany = null;
        } else {
            Optional<DTOAuthentication> dtoLoginOptional = userService.findByNitAndUser(user, companyNit);

            if (dtoLoginOptional.isEmpty()) {
                throw new CredentialsNotFound("No se ha encontrado el usuario con estas credenciales");
            }

            DTOAuthentication dtoAuthentication = dtoLoginOptional.get();
            encryptPasswd = dtoAuthentication.getPasswd();
            userStatus = dtoAuthentication.getUserStatus();

            if (dtoAuthentication.getCompanyStatus().equals(Status.INACTIVE)) {
                throw new InactiveCompanyException("Su empresa se encuentra inactiva, por favor realize el debido pago");
            }

            dtoUser = userService.findById(dtoAuthentication.getUserId());
            dtoCompany = companiesService.findCompanyInfo(dtoAuthentication.getCompanyId());
        }

        if (!BCryptEncryption.verifyPassword(passwd, encryptPasswd)) {
            throw new IncorrectPasswordException("Contraseña incorrecta");
        }

        if (userStatus.equals(Status.INACTIVE)) {
            throw new InactiveCompanyException("El usuario no esta activo");
        }

        Session.login(dtoUser, dtoCompany);
    }

}
