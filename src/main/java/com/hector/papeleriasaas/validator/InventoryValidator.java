package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.utils.ValidationUtils;

import java.util.Optional;

public final class InventoryValidator {

    public static void rowValidation(int row) throws InvalidInputException {
        if (row < 0) {
            throw new InvalidInputException("Seleccione el producto que desea modificar.");
        }
    }

    public static void searchParamValidation(String product) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(product, "Buscar Producto")) {
            throw new InvalidInputException("Por favor ingrese un término de búsqueda válido.");
        }
    }

    public static void dtoProductEmptyValidation(Optional<DTOProductDetails> dtoOptional, String param) throws ProductNotFoundException {
        if (dtoOptional.isEmpty()) {
            throw new ProductNotFoundException(param);
        }
    }

}
