package com.hector.papeleriasaas.model.dao.interfaces;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanCreate;
import com.hector.papeleriasaas.model.dto.plan.DTOPlanDetails;

public interface DAOPlans extends IListable<DTOPlanCreate>,
       IModifiable<DTOPlanDetails>,
       IRemovable {

    int add(DTOPlanCreate dto) throws DAOException;
    
}
