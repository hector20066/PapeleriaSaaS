package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.model.dto.sale.DTOSaleDetails;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class CalculateDashboardData {

    public static BigDecimal totalPurchases(List<DTOProductDetails> productsList) {
        return productsList.stream()
                .map(dto -> dto.getPurchasePrice().multiply(BigDecimal.valueOf(dto.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal totalSales(List<DTOSaleDetails> list) {
        return list.stream()
                .filter(distinctByNumSale())
                .map(DTOSaleDetails::getTotalSale)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static Predicate<DTOSaleDetails> distinctByNumSale() {
        Set<Integer> processed = new HashSet<>();
        return dto -> processed.add(dto.getNumSale());
    }

    public static BigDecimal totalProfits(List<DTOSaleDetails> salesList, List<DTOProductDetails> productsList) {
        return salesList.stream()
                .map(sale -> {
                    int quantitySold = sale.getQuantitySold();

                    Optional<DTOProductDetails> productOptional = productsList.stream()
                            .filter(p -> p.getId() == sale.getProductId())
                            .findFirst();

                    BigDecimal purchasePrice = productOptional
                            .map(DTOProductDetails::getPurchasePrice)
                            .orElse(BigDecimal.ZERO);

                    BigDecimal salePrice = productOptional
                            .map(DTOProductDetails::getSalePrice)
                            .orElse(BigDecimal.ZERO);

                    return (salePrice.multiply(BigDecimal.valueOf(quantitySold))).subtract(purchasePrice.multiply(BigDecimal.valueOf(quantitySold)));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
