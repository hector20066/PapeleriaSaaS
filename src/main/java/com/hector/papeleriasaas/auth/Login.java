package com.hector.papeleriasaas.auth;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.DataNotFoundException;
import com.hector.papeleriasaas.model.dao.interfaces.DAOCompanies;
import com.hector.papeleriasaas.model.dao.interfaces.DAOUsers;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.enums.Status;

import java.util.Optional;

public class Login {

    private final DAOUsers daoUsers;
    private final DAOCompanies daoCompanies;

    public Login(DAOUsers daoUsers, DAOCompanies daoCompanies) {
        this.daoUsers = daoUsers;
        this.daoCompanies = daoCompanies;
    }

    public DTOAuthentication login(String user, String passwd, int companyNit) throws DAOException {
        Optional<DTOAuthentication> dtoLoginOptional = daoUsers.findByNitAndUser(user, companyNit);

        if (dtoLoginOptional.isEmpty()) {
            throw new DataNotFoundException("No se ha encontrado el usuario con estas credenciales");
        }

        if (!BCryptEncryption.verifyPassword(passwd, dtoLoginOptional.get().getPasswd())) {
            throw new DataNotFoundException("Contraseña incorrecta");
        }

        DTOAuthentication dtoAuthentication = dtoLoginOptional.get();

        if (dtoAuthentication.getUserStatus().equals(Status.INACTIVE)) {
            throw new DAOException("El usuario no esta activo");
        }

        if (dtoAuthentication.getCompanyStatus().equals(Status.INACTIVE)) {
            throw new DAOException("Su empresa se encuentra inactiva, por favor realize el debido pago");
        }

        Optional<DTOUserDetails> userDto = daoUsers.findById(dtoAuthentication.getUserId());
        Optional<DTOCompanyDetails> companyDto = daoCompanies.findCompanyInfo(dtoAuthentication.getCompanyId());

        Session.login(userDto.orElseThrow(), companyDto.orElseThrow());

        return dtoAuthentication;
    }

}
