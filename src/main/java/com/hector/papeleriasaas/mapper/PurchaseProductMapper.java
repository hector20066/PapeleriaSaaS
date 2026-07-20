package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.validator.PurchaseProductValidator;

import java.math.BigDecimal;

public class PurchaseProductMapper {

    public static DTOProvider getDTOProvider(String nit, String rSocial, String address, String phone, String email) throws InvalidInputException {
        PurchaseProductValidator.registerPurchaseValidation(nit, rSocial, address, phone, email);

        return new DTOProvider.Builder()
                .nit(ParserUtils.parseInteger(nit, "nit del proveedor"))
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }

    public static DTOProductCreate toDTOProductCreate(String description, int amount, BigDecimal purchasePrice, BigDecimal salePrice) {
        DTOProductCreate dto = new DTOProductCreate();
        dto.setDescription(description);
        dto.setAmount(amount);
        dto.setPurchasePrice(purchasePrice);
        dto.setSalePrice(salePrice);

        return dto;
    }

}
