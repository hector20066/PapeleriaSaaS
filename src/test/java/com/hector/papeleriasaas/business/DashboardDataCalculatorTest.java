package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardDataCalculatorTest {

    private List<DTOProductDetails> productsList;
    private List<DTOSaleDetails> salesList;

    @BeforeEach
    void setUp() {
        this.productsList = new ArrayList<>();
        this.salesList = new ArrayList<>();

        productsList.add(new DTOProductDetails.Builder()
                .id(12345)
                .description("Cuaderno Norma")
                .purchasePrice(new BigDecimal("4500.00"))
                .salePrice(new BigDecimal("5000.00"))
                .amount(50)
                .earnings(new BigDecimal("25000.00"))
                .build());

        productsList.add(new DTOProductDetails.Builder()
                .id(12354)
                .description("Lapicero Offiesco Negro")
                .purchasePrice(new BigDecimal("1500.00"))
                .salePrice(new BigDecimal("2000.00"))
                .amount(20)
                .earnings(new BigDecimal("10000.00"))
                .build());

        salesList.add(new DTOSaleDetails.Builder()
                .numSale(122)
                .productId(12345)
                .quantitySold(20)
                .totalSale(new BigDecimal("100000.00"))
                .build());

        salesList.add(new DTOSaleDetails.Builder()
                .numSale(123)
                .productId(12354)
                .quantitySold(3)
                .totalSale(new BigDecimal("6000.00"))
                .build());
    }

    @Test
    @DisplayName("Testear el calculo del total de dinero en las compras.")
    void testTotalPurchases() {
        BigDecimal totalPurchases = DashboardDataCalculator.totalPurchases(productsList);
        
        assertAll(
                () -> assertNotNull(totalPurchases, () -> "El total de la compra es nulo"),
                () -> assertNotEquals(BigDecimal.ZERO, totalPurchases, () -> "El total de compras es 0"),
                () -> assertEquals(new BigDecimal("255000.00"), totalPurchases, () -> "El total de la compra no coincide con el valor esperado")
        );
    }

    @Test
    @DisplayName("Testear el calculo de ganancias de las ventas")
    void testTotalProfits() {
        BigDecimal totalProfits = DashboardDataCalculator.totalProfits(salesList, productsList);

        assertAll(
                () -> assertNotNull(totalProfits, () -> "Las ganancias son nulas"),
                () -> assertNotEquals(BigDecimal.ZERO, totalProfits, () -> "Las ganancias son 0"),
                () -> assertEquals(new BigDecimal("11500.00"), totalProfits, () -> "Las ganancias no coinciden con el valor esperado")
        );
    }

    @Test
    @DisplayName("Testear el calculo del total de las ventas")
    void testTotalSales() {
        BigDecimal totalSales = DashboardDataCalculator.totalSales(salesList);

        assertAll(
                () -> assertNotNull(totalSales, () -> "El total de las ventas es nul"),
                () -> assertNotEquals(BigDecimal.ZERO, totalSales, () -> "El total de las ventas es 0"),
                () -> assertEquals(new BigDecimal("106000.00"), totalSales, () -> "El total de las ventas no coincide con el valor esperado")
        );
    }

    @Test
    @DisplayName("Testear con listas vacías")
    void testTotalPurchasesEmpty() {
        BigDecimal result = DashboardDataCalculator.totalPurchases(new ArrayList<>());
        assertEquals(BigDecimal.ZERO, result, () -> "Listas vacías deben retornar ZERO");
    }

    @Test
    @DisplayName("Testear con listas nulas")
    @Tag("exception")
    void testTotalPurchasesNull() {
        assertThrows(NullPointerException.class, () -> DashboardDataCalculator.totalPurchases(null));
    }

    @Test
    @DisplayName("Testear con un solo producto")
    void testTotalPurchasesSingleItem() {
        List<DTOProductDetails> singleProduct = List.of(
                new DTOProductDetails.Builder()
                        .id(1)
                        .purchasePrice(new BigDecimal("1000.00"))
                        .amount(5)
                        .build()
        );

        assertEquals(new BigDecimal("5000.00"), DashboardDataCalculator.totalPurchases(singleProduct));
    }

}

