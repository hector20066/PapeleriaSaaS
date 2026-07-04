package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import org.junit.jupiter.api.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class ControllerPurchaseProductBusinessTest {

    private JTextField txt_nit;
    private JTextField txt_rSocial;
    private JTextField txt_address;
    private JTextField txt_phone;
    private JTextField txt_email;
    private JTable table;
    private DefaultTableModel model;

    @BeforeEach
    void setUp() {
        txt_nit = new JTextField();
        txt_nit.setName("txt_nit");
        txt_nit.setText("12345");

        txt_rSocial = new JTextField();
        txt_rSocial.setName("txt_rSocial");
        txt_rSocial.setText("Norma corporation");

        txt_address = new JTextField();
        txt_address.setName("txt_address");
        txt_address.setText("Tr 24 16-25");

        txt_phone = new JTextField();
        txt_phone.setName("txt_phone");
        txt_phone.setText("3137762626");

        txt_email = new JTextField();
        txt_email.setName("txt_email");
        txt_email.setText("norma@normacorporation.com");

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Producto", "Cantidad", "Precio Compra", "Precio Venta", "Total a pagar"});
        table.setModel(model);
    }

    @Test
    @DisplayName("Testear el método para la creación del DTOProvider")
    void testCreateDTOProvider() throws InvalidInputException {
        DTOProvider dtoProvider = ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone);

        assertAll(
                () -> assertNotNull(dtoProvider, () -> "La instancia del DTO es nula"),
                () -> assertEquals(12345, dtoProvider.getNit(), () -> "El nit no es el esperado"),
                () -> assertEquals("Norma corporation", dtoProvider.getRSocial(), () -> "El R. Social no es el esperado"),
                () -> assertEquals("Tr 24 16-25", dtoProvider.getAddress(), () -> "La dirección no es la esperada"),
                () -> assertEquals("3137762626", dtoProvider.getPhone(), () -> "El teléfono no es el esperado"),
                () -> assertEquals("norma@normacorporation.com", dtoProvider.getEmail(), () -> "El correo no es el esperado")
        );
    }

    @Test
    @DisplayName("Testear la creación de la lista de productos")
    void testCreateProductList() throws InvalidInputException {
        this.model.addRow(new Object[]{"Cuaderno Norma", 5, new BigDecimal("4500.00"), new BigDecimal("5000.00"), new BigDecimal("22500.00")});
        this.model.addRow(new Object[]{"Colores Norma", 1, new BigDecimal("15000.00"), new BigDecimal("17000.00"), new BigDecimal("15000.00")});

        List<DTOProductCreate> productList = ControllerPurchaseProductBusiness.getProductsList(model);

        assumeFalse(productList.isEmpty());

        assertAll(
                () -> assertEquals(2, productList.size(), () -> "El tamaño de la lista no es el esperado"),
                () -> assertNotNull(productList.getFirst(), () -> "El Producto en la primera posición es null"),
                () -> assertNotNull(productList.getLast(), () -> "El producto en la ultima posición es null"),
                () -> assertEquals("Cuaderno Norma", productList.getFirst().getDescription(), () -> "La descripción del primer producto no es la esperada"),
                () -> assertEquals(new BigDecimal("4500.00"), productList.getFirst().getPurchasePrice(), () -> "El precio de compra no es el esperado"),
                () -> assertEquals(5, productList.getFirst().getAmount(), () -> "La cantidad del primer producto no es el esperado"),
                () -> assertEquals(new BigDecimal("5000.00"), productList.getFirst().getSalePrice(), () -> "El precio de venta del primer producto no es el esperado")
        );
    }

    @Test
    @DisplayName("Testear el calculo del total a pagar")
    void testCalculateAmountPaid() throws InvalidInputException {
        this.model.addRow(new Object[]{"Cuaderno Norma", 5, new BigDecimal("4500.00"), new BigDecimal("5000.00"), new BigDecimal("22500.00")});
        this.model.addRow(new Object[]{"Colores Norma", 1, new BigDecimal("15000.00"), new BigDecimal("17000.00"), new BigDecimal("15000.00")});

        BigDecimal totalAmountPaid = ControllerPurchaseProductBusiness.totalAmountPaid(table);

        assertNotEquals(BigDecimal.ZERO, totalAmountPaid, () -> "El total de la compra es 0");
        assertEquals(new BigDecimal("37500.00"), totalAmountPaid, () -> "El total a pagar es el esperado");
    }

    @Nested
    @Tag("exception")
    @DisplayName("Testear el lanzamiento de excepciones")
    class ExceptionsTest {

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el Nit vacío")
        void testDtoProviderNitThrowsEmpty() {
            txt_nit.setText("");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para el Nit vacío."
            );

            assertEquals(EmptyFieldException.class, ex.getClass());
            assertEquals("El campo 'NIT del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el R. Social vacío")
        void testDtoProviderRSocialThrowsEmpty() {
            txt_rSocial.setText("");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para la Razón Social vacía."
            );

            assertEquals(EmptyFieldException.class, ex.getClass());
            assertEquals("El campo 'razón social del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando la Dirección vacía")
        void testDtoProviderAddressThrowsEmpty() {
            txt_address.setText("");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para la Dirección vacío."
            );

            assertEquals(EmptyFieldException.class, ex.getClass());
            assertEquals("El campo 'dirección del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el teléfono vacío")
        void testDtoProviderPhoneThrowsEmpty() {
            txt_phone.setText("");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para el Teléfono vacío."
            );

            assertEquals(EmptyFieldException.class, ex.getClass());
            assertEquals("El campo 'teléfono del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando el correo vacío")
        void testDtoProviderEmailThrowsEmpty() {
            txt_email.setText("");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para el correo vacío."
            );

            assertEquals(EmptyFieldException.class, ex.getClass());
            assertEquals("El campo 'correo del proveedor' es requerido y no puede estar vacío", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado");
        }

        @Test
        @Tag("number_exception")
        @DisplayName("Testeando el Nit con valores no numéricos")
        void testCreateDtoProviderThrowsNumeric() {
            txt_nit.setText("123d");

            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getDTOProvider(txt_nit, txt_email, txt_rSocial, txt_address, txt_phone),
                    () -> "La excepción lanzada no es la esperada para el Nit con valores no numéricos"
            );

            assertEquals(InvalidNumberInputException.class, ex.getClass());
            assertEquals("El campo 'NIT del proveedor' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje de la excepcion no es el esperado");
        }

        @Test
        @Tag("empty_exception")
        @DisplayName("Testeando la creación de la lista con la tabla vacía")
        void testProductListThrowsEmpty() {
            InvalidInputException ex = assertThrows(
                    InvalidInputException.class,
                    () -> ControllerPurchaseProductBusiness.getProductsList(model),
                    () -> "La excepción lanzada no es la esperada"
            );

            assertEquals("Agregue al menos un producto antes de registrar la compra.", ex.getMessage(), () -> "El mensaje de error no es el esperado.");
        }

        @Test
        @Tag("number_exception")
        @DisplayName("Testear el calculo del total a pagar con valores no numéricos")
        void testAmountPaidThrows() {
            model.addRow(new Object[]{"Cuaderno Norma", 5, new BigDecimal("4500.00"), new BigDecimal("5000.00"), "22500d"});

            InvalidNumberInputException ex = assertThrows(
                    InvalidNumberInputException.class,
                    () -> ControllerPurchaseProductBusiness.totalAmountPaid(table),
                    () -> "La excepción lanzada no es la esperada."
            );

            assertEquals("El campo 'total pagado' debe contener un valor numérico", ex.getMessage(), () -> "El mensaje no es el esperado");
        }

    }

}
