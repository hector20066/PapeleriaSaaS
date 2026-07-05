package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseProductMapperTest {

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
    @DisplayName("Testear la creación del DTOProvider")
    void testCreateDtoProvider() throws InvalidInputException {
        DTOProvider dto = PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email);

        assertAll(
                () -> assertNotNull(dto, () -> "La instancia del DTO es nula"),
                () -> assertEquals(123456789, dto.getNit(), () -> "El nit no es el esperado"),
                () -> assertEquals("Norma Corporation", dto.getRSocial(), () -> "El R. Social no es el esperado"),
                () -> assertEquals("Tr 24 16-25", dto.getAddress(), () -> "La dirección no es la esperada"),
                () -> assertEquals("3137762626", dto.getPhone(), () -> "El teléfono no es el esperado"),
                () -> assertEquals("norma@normacorporation.com", dto.getEmail(), () -> "El correo no es el esperado")
        );
    }

    @Test
    @DisplayName("Testear la creación del DTOProductCreate")
    void testCreateDtoProductCreate() {
        String description = "Cuaderno Norma";
        int amount = 5;
        BigDecimal purchasePrice = new BigDecimal("4500.00");
        BigDecimal salePrice = new BigDecimal("5500.00");

        DTOProductCreate dto = PurchaseProductMapper.toDTOProductCreate(description, amount, purchasePrice, salePrice);

        assertAll(
                () -> assertNotNull(dto, () -> "El DTO es null"),
                () -> assertEquals("Cuaderno Norma", dto.getDescription(), () -> "El producto no es el esperado"),
                () -> assertEquals(5, dto.getAmount(), () -> "La cantidad no es la esperada"),
                () -> assertEquals(new BigDecimal("4500.00"), dto.getPurchasePrice(), () -> "El precio de compra no coincide"),
                () -> assertEquals(new BigDecimal("5500.00"), dto.getSalePrice(), () -> "El precio de la venta no es el esperado")
        );
    }

    @Nested
    @Tag("exception")
    @DisplayName("Testear el lanzamiento de excepciones")
    class ExceptionsTest {

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el Nit vacío")
        void testDtoProviderNitThrowsEmpty() {
            nit = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Nit vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("El campo 'NIT del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el R. Social vacío")
        void testDtoProviderRSocialThrowsEmpty() {
            rSocial = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Razón Social vacía."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("El campo 'razón social del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando la Dirección vacía")
        void testDtoProviderAddressThrowsEmpty() {
            address = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para la Dirección vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("El campo 'dirección del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el teléfono vacío")
        void testDtoProviderPhoneThrowsEmpty() {
            phone = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Teléfono vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("El campo 'teléfono del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el correo vacío")
        void testDtoProviderEmailThrowsEmpty() {
            email = "";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertInstanceOf(EmptyFieldException.class, ex);
            assertEquals("El campo 'correo del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("number_exception")
        @DisplayName("Testeando el Nit con valores no numéricos")
        void testCreateDtoProviderThrowsNumeric() {
            nit = "123d";

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> PurchaseProductMapper.getDTOProvider(nit, rSocial, address, phone, email),
                    () -> "La excepción lanzada no es la esperada para el Nit con valores no numéricos"
            );

            assertInstanceOf(InvalidNumberInputException.class, ex);
            assertEquals("El campo 'NIT del proveedor' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

    }

}
