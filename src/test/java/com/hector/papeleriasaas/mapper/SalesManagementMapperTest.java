package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.controller.dto.OrderItemSaleDTO;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesManagementMapperTest {

    @Test
    @DisplayName("Testear la creación de la lista de detalles de la venta")
    void testProductDetails() throws InvalidInputException {
        List<OrderItemSaleDTO> orderList = new ArrayList<>();
        orderList.add(new OrderItemSaleDTO("Cuaderno Norma", 12345, 2, new BigDecimal("5000.00")));
        orderList.add(new OrderItemSaleDTO("Colores Norma", 12354, 1, new BigDecimal("16000.00")));
        orderList.add(new OrderItemSaleDTO("Lapicero Negro Offiesco", 12435, 3, new BigDecimal("2000.00")));
        orderList.add(new OrderItemSaleDTO("Lapicero Rojo Offiesco", 21435, 2, new BigDecimal("2000.00")));

        List<DTOSaleDetailsCreate> list = SalesManagementMapper.listProductDetails(orderList);
        DTOSaleDetailsCreate dto1 = list.getFirst();
        DTOSaleDetailsCreate dto2 = list.get(2);

        assertAll(
                () -> assertEquals(4, list.size(), () -> "El tamaño de la lista no es el esperado."),
                () -> assertNotNull(dto1, () -> "El primer DTO creado es null"),
                () -> assertNotNull(dto2, () -> "El segundo DTO creado es null"),

                () -> assertEquals(12345, dto1.getProductId(), () -> "El id del primer producto no es el esperado"),
                () -> assertEquals(2, dto1.getAmount(), () -> "La cantidad del primer producto no es el esperado"),
                () -> assertEquals(new BigDecimal("5000.00"), dto1.getUnitPrice(), () -> "El precio del primer producto no es el esperado"),

                () -> assertEquals(12435, dto2.getProductId(), () -> "El id del segundo producto no es el esperado"),
                () -> assertEquals(3, dto2.getAmount(), () -> "La cantidad del segundo producto no es el esperado"),
                () -> assertEquals(new BigDecimal("2000.00"), dto2.getUnitPrice(), () -> "El precio del segundo producto no es el esperado")
        );
    }

    @Test
    @Tag("exception")
    @DisplayName("Testear la excepción lanzada al crear la lista de los detalles.")
    void testProductDetailsException() {
        InvalidInputException ex = assertThrows(
                InvalidInputException.class,
                () -> SalesManagementMapper.listProductDetails(Collections.emptyList()),
                () -> "La excepción lanzada no es la esperada"
        );

        assertInstanceOf(InvalidInputException.class, ex, () -> "La excepción lanzada no es del tipo esperado");
        assertEquals("Agregue al menos un producto al carrito antes de registrar la venta.", ex.getMessage(), () -> "El mensaje de la excepción no es el esperado.");
    }

}