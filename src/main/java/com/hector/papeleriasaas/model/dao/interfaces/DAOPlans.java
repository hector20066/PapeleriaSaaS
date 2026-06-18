package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyCreate;
import com.hector.papeleriasaas.model.dto.company.DTOCompanyDetails;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanUpdate;

public interface DAOPlans extends IListable<DTOPlanCreate>,
       IModifiable<DTOPlanUpdate>,
       IRemovable {

    int add(DTOPlanCreate dto) throws DAOException;
    
}
