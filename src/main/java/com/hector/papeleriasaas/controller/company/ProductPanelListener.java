package com.hector.papeleriasaas.controller.company;

import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;

import javax.swing.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface ProductPanelListener {

    void onIncreaseQuantity(DTOProductDetails product, JLabel quantityLabel);
    void onDecreaseQuantity(DTOProductDetails product, JLabel quantityLabel);
    void onAddToOrder(DTOProductDetails product, int quantity);

}
