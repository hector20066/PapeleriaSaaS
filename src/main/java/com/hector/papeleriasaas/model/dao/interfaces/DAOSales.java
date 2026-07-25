package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleCreate;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleUpdate;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface DAOSales extends IModifiable<DTOSaleUpdate>,
        ICountable {

    int add(Connection connection, DTOSaleCreate dto) throws DAOException;
    List<DTOSaleDetails> findById(int id, int companyId) throws DAOException;
    List<DTOSaleDetails> findById(Connection connection, int id, int companyId) throws DAOException;
    List<DTOSaleDetails> findByAll(int companyId) throws DAOException;
    void modify(DTOSaleUpdate dto) throws DAOException;

}
