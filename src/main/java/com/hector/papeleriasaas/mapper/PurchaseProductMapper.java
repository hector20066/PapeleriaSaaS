package com.hector.papeleriasaas.mapper;

import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.model.dto.provider.DTOProvider;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.validator.PurchaseProductValidator;

import java.math.BigDecimal;

public class PurchaseProductMapper {

    public static DTOProvider getDTOProvider(String nitStr, String rSocial, String address, String phoneStr, String emailStr) throws InvalidInputException {
        PurchaseProductValidator.registerPurchaseValidation(nitStr, rSocial, address, phoneStr, emailStr);

        Integer nit = nitStr.equalsIgnoreCase("NIT") || nitStr.isBlank()
                ? null
                : ParserUtils.parseInteger(nitStr, "nit del proveedor");

        String phone = phoneStr.equalsIgnoreCase("Telefono") || phoneStr.isBlank() ? null : phoneStr;
        String email = emailStr.equalsIgnoreCase("Correo") || emailStr.isBlank() ? null : emailStr;

        return new DTOProvider.Builder()
                .nit(nit)
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
