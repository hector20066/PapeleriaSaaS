package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ModifyProductValidatorTest {

    private String id;
    private String product;
    private String amount;
    private String purchasePrice;
    private String salePrice;

    @BeforeEach
    void setUp() {
        this.id = "112345";
        this.product = "Cuaderno Norma";
        this.amount = "5";
        this.purchasePrice = "25000.00";
        this.salePrice = "27000.00";
    }

    @Test
    void testFieldNumericValue() {
        this.id = "12304O";
        this.amount = "5S";

        InvalidNumberInputException ex = assertThrows(
                InvalidNumberInputException.class,
                () -> ModifyProductValidator.fieldsValidation(id, product, amount, purchasePrice, salePrice),
                () -> "La exception lanzada no es la esperada"
        );

        assertEquals("El campo 'ID y cantidad' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de error lanzado no es el esperado.");
        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no es instancia de InvalidInputException");
    }

    @Test
    void testProductAmount() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(-1, new BigDecimal("5000.00"), new BigDecimal("4000.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("La cantidad disponible no puede ser negativa.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    void testProductPurchasePrices() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(6, new BigDecimal("-5000.00"), new BigDecimal("4000.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los precios no pueden ser negativos.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    void testProductSalePrices() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(3, new BigDecimal("5000.00"), new BigDecimal("-6570.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los precios no pueden ser negativos.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Nested
    @Tag("empty_exception")
    class EmptyFieldsTest {

        @Test
        void testEmptyFieldId() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation("", product, amount, purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: ID.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testEmptyFieldProduct() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, "", amount, purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Producto.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testEmptyFieldAmount() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, "", purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Cantidad.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testEmptyFieldPurchasePrice() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, amount, "", salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Precio de compra.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testEmptyFieldSalePrice() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, amount, purchasePrice, ""),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Precio de venta.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testEmptyFields() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation("", "", "", "", ""),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: ID, Producto, Cantidad, Precio de compra, Precio de venta.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

    }

    @Nested
    @Tag("null_exception")
    class NullFieldsTest {

        @Test
        void testNullFieldId() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(null, product, amount, purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: ID.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testNullFieldProduct() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, null, amount, purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Producto.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testNullFieldAmount() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, null, purchasePrice, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Cantidad.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testNullFieldPurchasePrice() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, amount, null, salePrice),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Precio de compra.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testNullFieldSalePrice() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(id, product, amount, purchasePrice, null),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: Precio de venta.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

        @Test
        void testNullFields() {
            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> ModifyProductValidator.fieldsValidation(null, null, null, null, null),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Los siguientes campos son requeridos: ID, Producto, Cantidad, Precio de compra, Precio de venta.", ex.getMessage());
            assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no hereda de InvalidInputException");
        }

    }

}