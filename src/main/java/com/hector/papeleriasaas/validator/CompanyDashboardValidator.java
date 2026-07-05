package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;

import java.util.Optional;

public final class CompanyDashboardValidator {

    public static void bestSellingEmptyValidation(Optional<DTOProductBestSelling> bestSelling) throws ProductNotFoundException {
        if (bestSelling.isEmpty()) {
            throw new ProductNotFoundException();
        }
    }

}
