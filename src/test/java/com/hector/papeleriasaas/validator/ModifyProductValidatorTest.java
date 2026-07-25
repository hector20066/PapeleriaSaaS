package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
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
    @DisplayName("Testear validación para valores numéricos")
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

    /* @Test
    @DisplayName("Testear validación para la cantidad")
    void testProductAmount() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(-1, new BigDecimal("5000.00"), new BigDecimal("4000.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("La cantidad disponible no puede ser negativa.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    @DisplayName("Testear el precio de compra del producto")
    void testProductPurchasePrices() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(6, new BigDecimal("0"), new BigDecimal("4000.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los precios no pueden ser negativos o ceros", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    }

    @Test
    @DisplayName("Testear el precio de venta del producto")
    void testProductSalePrices() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> ModifyProductValidator.productValidation(3, new BigDecimal("5000.00"), new BigDecimal("-1.00")),
                () -> "La excepción lanzada no es la esperada"
        );

        assertEquals("Los precios no pueden ser negativos o ceros", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
    } */

    @Nested
    @Tag("empty_exception")
    @DisplayName("Testear excepciones para campos vacíos")
    class EmptyFieldsTest {

        @Test
        @DisplayName("Testear ID vacío")
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
        @DisplayName("Testear campos de productos vacíos")
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
        @DisplayName("Testear cantidad vacía")
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
        @DisplayName("Testear precio de compra vacío")
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
        @DisplayName("Testear precio de venta vacío")
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
        @DisplayName("Testear campos vacíos")
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
    @DisplayName("Testear excepciones para campos nulos")
    class NullFieldsTest {

        @Test
        @DisplayName("Testear ID nul")
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
        @DisplayName("Testear campos de productos nulos")
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
        @DisplayName("Testear cantidad nula")
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
        @DisplayName("Testear precio de compra nulo")
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
        @DisplayName("Testear precio de venta nulo")
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
        @DisplayName("Testear campos nulos")
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