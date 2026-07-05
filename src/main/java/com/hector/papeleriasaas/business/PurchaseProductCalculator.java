package com.hector.papeleriasaas.business;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseProductCalculator {

    public static BigDecimal totalAmountPaid(List<BigDecimal> listTotalPaid) {
        return listTotalPaid.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
