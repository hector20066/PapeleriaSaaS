package com.hector.papeleriasaas.business;

import com.hector.papeleriasaas.exception.validation.EmptyFieldException;
import com.hector.papeleriasaas.exception.validation.InvalidInputException;
import com.hector.papeleriasaas.exception.validation.InvalidNumberInputException;
import com.hector.papeleriasaas.model.dto.DTOProvider;
import com.hector.papeleriasaas.model.dto.product.DTOProductCreate;
import com.hector.papeleriasaas.utils.ParserUtils;
import com.hector.papeleriasaas.utils.ValidationUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ControllerPurchaseProductBusiness {

    public static DTOProvider getDTOProvider(JTextField... fields) throws InvalidInputException {
        String nit = "";
        String rSocial = "";
        String address = "";
        String phone = "";
        String email = "";

        for (JTextField field : fields) {
            switch (field.getName()) {
                case "txt_nit" -> nit = field.getText().trim();
                case "txt_rSocial" -> rSocial = field.getText().trim();
                case "txt_address" -> address = field.getText().trim();
                case "txt_phone" -> phone = field.getText().trim();
                case "txt_email" -> email = field.getText().trim();
            }
        }

        validations(nit, rSocial, address, phone, email);

        return new DTOProvider.Builder()
                .nit(ParserUtils.parseInteger(nit, "nit del proveedor"))
                .rSocial(rSocial)
                .address(address)
                .phone(phone)
                .email(email)
                .build();
    }

    private static void validations(String nit, String rSocial, String address, String phone, String email) throws InvalidInputException {
        if (ValidationUtils.isInvalidInput(nit, "Nit")) {
            throw new EmptyFieldException("NIT del proveedor");
        }

        if (ValidationUtils.isInvalidInput(rSocial, "R. Social")) {
            throw new EmptyFieldException("razón social del proveedor");
        }

        if (ValidationUtils.isInvalidInput(address, "Direccion")) {
            throw new EmptyFieldException("dirección del proveedor");
        }

        if (ValidationUtils.isInvalidInput(phone, "Telefono")) {
            throw new EmptyFieldException("teléfono del proveedor");
        }

        if (ValidationUtils.isInvalidInput(email, "Correo")) {
            throw new EmptyFieldException("correo del proveedor");
        }

        if (!ValidationUtils.isNumeric(nit)) {
            throw new InvalidNumberInputException("NIT del proveedor");
        }
    }

    public static List<DTOProductCreate> getProductsList(DefaultTableModel tbl_model) throws InvalidInputException {
        List<DTOProductCreate> list = new ArrayList<>();

        for (int i = 0; i < tbl_model.getRowCount(); i++) {
            String product = tbl_model.getValueAt(i, 0).toString();
            int amount = ParserUtils.parseInteger(tbl_model.getValueAt(i, 1).toString(), "cantidad");
            BigDecimal purchasePrice = ParserUtils.parseBigDecimal(tbl_model.getValueAt(i, 2).toString(),
                    "precio de compra");
            BigDecimal salePrice = ParserUtils.parseBigDecimal(tbl_model.getValueAt(i, 3).toString(),
                    "precio de venta");

            DTOProductCreate dto = new DTOProductCreate();
            dto.setDescription(product);
            dto.setAmount(amount);
            dto.setPurchasePrice(purchasePrice);
            dto.setSalePrice(salePrice);

            list.add(dto);
        }

        if (list.isEmpty()) {
            throw new InvalidInputException("Agregue al menos un producto antes de registrar la compra.");
        }

        return list;
    }

    public static BigDecimal totalAmountPaid(JTable jTable) throws InvalidInputException {
        BigDecimal totalAmountPaid = BigDecimal.ZERO;

        for (int i = 0; i < jTable.getRowCount(); i++) {
            BigDecimal totalPaid = ParserUtils.parseBigDecimal(jTable
                    .getValueAt(i, 4).toString(), "total pagado");
            totalAmountPaid = totalAmountPaid.add(totalPaid);
        }

        return totalAmountPaid;
    }

}
