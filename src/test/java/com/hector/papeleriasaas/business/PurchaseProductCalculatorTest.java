package com.hector.papeleriasaas.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PurchaseProductCalculatorTest {

    @Test
    @DisplayName("Testear el calculo del total a pagar")
    void testCalculateAmountPaid() {
        List<BigDecimal> listTotalPaid = new ArrayList<>();
        listTotalPaid.add(new BigDecimal("25000.00"));
        listTotalPaid.add(new BigDecimal("10000.00"));
        listTotalPaid.add(new BigDecimal("5000.99"));
        listTotalPaid.add(new BigDecimal("33000.00"));

        BigDecimal totalAmountPaid = PurchaseProductCalculator.totalAmountPaid(listTotalPaid);

        assertNotEquals(BigDecimal.ZERO, totalAmountPaid, () -> "El total calculado es 0");
        assertEquals(new BigDecimal("73000.99"), totalAmountPaid, () -> "El total a pagar no es el esperado");
    }

}
