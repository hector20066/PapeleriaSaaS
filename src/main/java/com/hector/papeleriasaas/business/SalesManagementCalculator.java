package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.controller.dto.OrderItemSaleDTO;

import java.math.BigDecimal;
import java.util.List;

public class SalesManagementCalculator {

    public static BigDecimal getTotalSale(List<OrderItemSaleDTO> orderList) {
        return orderList.stream()
                .map(dto -> dto.getUnitPrice().multiply(new BigDecimal(dto.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
