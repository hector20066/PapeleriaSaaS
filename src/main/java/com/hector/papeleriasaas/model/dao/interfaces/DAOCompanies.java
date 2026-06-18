package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyUpdate;

import java.util.Optional;

public interface DAOCompanies extends IListable<DTOCompanyDetails>,
        IModifiable<DTOCompanyUpdate>,
        IRemovable {

    int add(DTOCompanyCreate dto) throws DAOException;
    Optional<DTOCompanyDetails> findByName(String name) throws DAOException;
    Optional<DTOCompanyDetails> findByNit(int id) throws DAOException;

}
