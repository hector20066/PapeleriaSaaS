package com.hector.papeleriasaas.validator;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.mapper.PurchaseProductMapper;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("validator")
class PurchaseProductValidatorTest {

    private String nit;
    private String rSocial;
    private String address;
    private String phone;
    private String email;

    @BeforeEach
    void setUp() {
        nit = "123456789";
        rSocial = "Norma Corporation";
        address = "Tr 24 16-25";
        phone = "3137762626";
        email = "norma@normacorporation.com";
    }

    @Test
    @Tag("number_exception")
    @DisplayName("Testeando el Nit con valores no numéricos")
    void testNitValueNumber() {
        nit = "123d";

        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                () -> "La excepción lanzada no es la esperada para el Nit con valores no numéricos"
        );

        assertInstanceOf(InvalidNumberInputException.class, ex);
        assertEquals("El campo 'NIT del proveedor' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
    }

    @Nested
    @Tag("empty_exception")
    @DisplayName("Testear campos vacíos")
    class EmptyFieldsTest {

        @Test
        @DisplayName("Testeando el R. Social vacío")
        void testRSocialEmpty() {
            rSocial = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Razón Social vacía."
            );

            assertInstanceOf(InvalidInputException.class, ex);
            assertEquals("Los siguientes campos son requeridos: R. Social.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testeando la Dirección vacía")
        void testAddressEmpty() {
            address = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Dirección vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Dirección.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testeando los campos vacío")
        void testFieldsEmpty() {
            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation("", "", "", "", ""),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: R. Social, Dirección.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

    @Nested
    @DisplayName("Testear campos nulos")
    class NullFieldsTest {

        @Test
        @DisplayName("Testear el R. Social null")
        void testRSocialEmpty() {
            rSocial = null;

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Razón Social vacía."
            );

            assertInstanceOf(InvalidInputException.class, ex);
            assertEquals("Los siguientes campos son requeridos: R. Social.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testear la Dirección null")
        void testAddressEmpty() {
            address = null;

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Dirección vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Dirección.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testear todos los parámetros null")
        void testFieldsEmpty() {
            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation("", "", "", "", ""),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: R. Social, Dirección.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

    @Nested
    @DisplayName("Testear campos con valores por defecto")
    class InvalidInputTest {

        @Test
        @DisplayName("Testear el R. Social con texto por defecto")
        void testDtoProviderRSocialThrowsEmpty() {
            rSocial = "R. Social";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Razón Social vacía."
            );

            assertEquals("La razón social del proveedor es requerida, ingrese un valor valido", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testear la Dirección con texto por defecto")
        void testDtoProviderAddressThrowsEmpty() {
            address = "Direccion";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Dirección vacío."
            );

            assertEquals("La dirección del proveedor es requerida, ingrese un valor valido", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

}