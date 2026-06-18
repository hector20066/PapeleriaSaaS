package com.hector.papeleriasaas.model.dao.interfaces;

import java.util.Optional;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.user.DTOUserCreate;
import com.hector.papeleriasaas.model.dto.user.DTOUserDetails;
import com.hector.papeleriasaas.model.dto.user.DTOUserUpdate;

public interface DAOUsers extends IListable<DTOUserDetails>,
       IModifiable<DTOUserUpdate>,
       IRemovable {

    int add(DTOUserCreate dto) throws DAOException;
    Optional<DTOUserDetails> findByIdentification(int identification) throws DAOException;
    Optional<DTOUserDetails> findByUser(String user) throws DAOException;
    Optional<DTOUserDetails> validateCredentials(String user, String passwd) throws DAOException;

}
