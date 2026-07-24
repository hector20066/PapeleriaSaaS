package com.hector.papeleriasaas.service.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProvidersService {

    void add(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;
    Optional<DTOProvider> findByName(String name) throws DAOException;
    Optional<DTOProvider> findById(int id) throws DAOException;
    Optional<DTOProvider> findById(Connection connection, int id) throws DAOException;
    List<DTOProvider> findByAll() throws DAOException;
    void modify(Connection connection, int nit, String rSocial, String address, String phone, String email) throws DAOException;
    void delete(int id) throws DAOException;
    int countRecords() throws DAOException;

}
