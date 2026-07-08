package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.dao.DAOException;
import com.hector.papeleriasaas.exception.dao.notfound.ProductNotFoundException;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
class CompanyDashboardValidatorTest {

    @Test
    @DisplayName("Validar excepción lanzada")
    void testBestSellingEmpty() {
        Optional<DTOProductBestSelling> dto = Optional.empty();

        ProductNotFoundException ex = assertThrows(
                ProductNotFoundException.class,
                () -> CompanyDashboardValidator.bestSellingEmptyValidation(dto),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Producto no encontrado", ex.getMessage());
        assertInstanceOf(DAOException.class, ex, () -> "La instancia actual no hereda de DAOException");
    }

}