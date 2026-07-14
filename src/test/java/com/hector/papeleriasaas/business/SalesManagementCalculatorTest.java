package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.controller.dto.OrderItemSaleDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SalesManagementCalculatorTest {

    @Test
    @DisplayName("Testear el calculo del total de la venta")
    void testCalculateTotalSale() {
        List<OrderItemSaleDTO> orderList = new ArrayList<>();
        orderList.add(new OrderItemSaleDTO("Cuaderno Norma", 12345, 2, new BigDecimal("5000.00")));
        orderList.add(new OrderItemSaleDTO("Colores Norma", 12354, 1, new BigDecimal("16000.00")));
        orderList.add(new OrderItemSaleDTO("Lapicero Negro Offiesco", 12435, 3, new BigDecimal("2000.00")));
        orderList.add(new OrderItemSaleDTO("Lapicero Rojo Offiesco", 21435, 2, new BigDecimal("2000.00")));

        BigDecimal totalSale = SalesManagementCalculator.getTotalSale(orderList);

        assertNotEquals(BigDecimal.ZERO, totalSale, () -> "El total de la venta es 0");
        assertEquals(new BigDecimal("36000.00"), totalSale, () -> "El total de la venta no coincide");
    }

    @Test
    @DisplayName("Testear el calculo con un solo producto")
    void testCalculateTotalSaleSingleItem() {
        List<OrderItemSaleDTO> orderList = new ArrayList<>();
        orderList.add(new OrderItemSaleDTO("Cuaderno Norma", 12345, 2, new BigDecimal("5000.00")));

        BigDecimal totalSale = SalesManagementCalculator.getTotalSale(orderList);

        assertNotEquals(BigDecimal.ZERO, totalSale, () -> "El total calculado es 0");
        assertEquals(new BigDecimal("10000.00"), totalSale, () -> "El total a pagar no es el esperado");
    }

    @Test
    @DisplayName("Testear con listas vacías")
    void testAmountPaidEmpty() {
        BigDecimal result = SalesManagementCalculator.getTotalSale(Collections.emptyList());
        assertEquals(BigDecimal.ZERO, result, () -> "Listas vacías deben retornar ZERO");
    }

}