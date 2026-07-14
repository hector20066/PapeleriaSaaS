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
        @DisplayName("Testeando el Nit vacío")
        void testNitEmpty() {
            nit = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Nit vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: NIT.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

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
        @DisplayName("Testeando el teléfono vacío")
        void testPhoneEmpty() {
            phone = "";

            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Teléfono vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Teléfono.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testeando el correo vacío")
        void testEmailEmpty() {
            email = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Correo.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
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
            assertEquals("Los siguientes campos son requeridos: NIT, R. Social, Dirección, Teléfono, Correo.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

    @Nested
    @DisplayName("Testear campos nulos")
    class NullFieldsTest {

        @Test
        @DisplayName("Testear el Nit null")
        void testNitEmpty() {
            nit = null;

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Nit vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: NIT.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

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
        @DisplayName("Testear el teléfono null")
        void testPhoneEmpty() {
            phone = null;

            EmptyFieldException ex = assertThrows(
                    EmptyFieldException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Teléfono vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Teléfono.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testear el correo null")
        void testEmailEmpty() {
            email = null;

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("Los siguientes campos son requeridos: Correo.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
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
            assertEquals("Los siguientes campos son requeridos: NIT, R. Social, Dirección, Teléfono, Correo.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

    @Nested
    @DisplayName("Testear campos con valores por defecto")
    class InvalidInputTest {

        @Test
        @DisplayName("Testear el Nit con texto por defecto")
        void testDtoProviderNitThrowsEmpty() {
            nit = "Nit";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Nit vacío."
            );

            assertEquals("El NIT del proveedor es requerido, ingrese un valor valido", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

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

        @Test
        @DisplayName("Testear el teléfono con texto por defecto")
        void testDtoProviderPhoneThrowsEmpty() {
            phone = "Telefono";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Teléfono vacío."
            );

            assertEquals("El teléfono del proveedor es requerido, ingrese un valor valido", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @DisplayName("Testear el correo con texto por defecto")
        void testDtoProviderEmailThrowsEmpty() {
            email = "Correo";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertEquals("El correo del proveedor es requerido, ingrese un valor valido", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

}