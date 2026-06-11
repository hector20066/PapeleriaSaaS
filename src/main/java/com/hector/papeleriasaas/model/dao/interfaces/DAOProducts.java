package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.product.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOProducts extends IListable<DTOProductDetails>,
        IModifiable<DTOProductUpdate>,
        IRemovable,
        ICountable {

    int add(Connection connection, DTOProductCreate dto) throws DAOException;
    Optional<DTOProductDetails> findByName(String product) throws DAOException;
    Optional<DTOProductDetails> findByName(Connection connection, String product) throws DAOException;
    Optional<DTOProductBestSelling> bestSelling() throws DAOException;
    List<DTOProductDetails> getProductList();
    void modify(DTOProductUpdate dto) throws DAOException;

}
