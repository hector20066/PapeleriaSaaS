package com.hector.papeleriasaas.controller.company;

import static org.junit.jupiter.api.Assertions.*;

import com.hector.papeleriasaas.controller.dto.DTOReportSummary;
import com.hector.papeleriasaas.model.dto.product.DTOProductBestSelling;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ControllerDashboardTest {

    List<DTOProductDetails> productList;
    List<DTOSaleDetails> salesList;
    DTOProductBestSelling bestSelling;

    @BeforeEach
    void beforeEach() {
        this.productList = new ArrayList<>();
        this.salesList = new ArrayList<>();

        this.bestSelling = new DTOProductBestSelling.Builder()
                .description("Cuaderno Norma")
                .timesSold(50)
                .build();

        this.productList.add(new DTOProductDetails.Builder()
                .id(33)
                .description("Cuaderno Norma")
                .amount(50)
                .purchasePrice(new BigDecimal("4500.00"))
                .salePrice(new BigDecimal("5000.00"))
                .provider("Norma corporation")
                .earnings(new BigDecimal("50000.00"))
                .build());

        this.salesList.add(new DTOSaleDetails.Builder()
                .numSale(23)
                .productId(33)
                .productDescription("Cuaderno Norma")
                .quantitySold(10)
                .date(LocalDate.of(2026, 5, 27))
                .customer("Hector Acevedo")
                .totalSale(new BigDecimal("30000.00"))
                .build());

        this.salesList.add(new DTOSaleDetails.Builder()
                .numSale(24)
                .productId(33)
                .productDescription("Cuaderno Norma")
                .quantitySold(5)
                .date(LocalDate.of(2026, 5, 27))
                .customer("Hector Acevedo")
                .totalSale(new BigDecimal("30000.00"))
                .build());
    }

    @Test
    @DisplayName("Testear la creación del DTOReportSummary")
    void testCreateReportSummary() {
        DTOReportSummary dto = ControllerDashboard.getReportSummary(this.productList, this.salesList, this.bestSelling);

        assertAll(
                () -> assertNotNull(dto, () -> "La instancia no puede ser nula"),
                () -> assertEquals(225000f, dto.getTotalPurchases(), () -> "El total de la compra no es el esperado"),
                () -> assertEquals(60000f, dto.getTotalSales(), () -> "El total de la venta no es el esperado"),
                () -> assertEquals(7500f, dto.getTotalProfits(), () -> "La ganancias no son las esperadas"),
                () -> assertEquals("Cuaderno Norma", dto.getProductName(), () -> "El producto no es el esperado"),
                () -> assertEquals(50, dto.getAmountProduct(), () -> "La cantidad del producto no es la esperada")
        );
    }

}
