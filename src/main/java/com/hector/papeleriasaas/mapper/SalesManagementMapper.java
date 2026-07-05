package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.controller.dto.OrderItemSaleDTO;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.DTOSaleDetailsCreate;

import java.util.List;

public class SalesManagementMapper {

    public static List<DTOSaleDetailsCreate> listProductDetails(List<OrderItemSaleDTO> orderList) throws InvalidInputException {
        List<DTOSaleDetailsCreate> list = orderList.stream()
                .map(orderDto -> {
                    DTOSaleDetailsCreate dto = new DTOSaleDetailsCreate();
                    dto.setProductId(orderDto.getProductId());
                    dto.setAmount(orderDto.getAmount());
                    dto.setUnitPrice(orderDto.getUnitPrice());

                    return dto;
                })
                .toList();

        if (list.isEmpty()) {
            throw new InvalidInputException("Agregue al menos un producto al carrito antes de registrar la venta.");
        }

        return list;
    }

}
