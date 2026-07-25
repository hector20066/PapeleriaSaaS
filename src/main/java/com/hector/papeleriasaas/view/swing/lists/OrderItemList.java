package com.hector.papeleriasaas.view.swing.lists;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class OrderItemList extends JPanel {

    private static final Color BG_LIST = new Color(255, 255, 255);
    private static final Color COLOR_BORDER = new Color(226, 232, 240);

    private final DefaultListModel<OrderItem> model;
    private final JList<OrderItem> list;

    public OrderItemList() {
        setLayout(new BorderLayout());
        setBackground(BG_LIST);
        setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1, true));

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setCellRenderer(new ItemCellRenderer());
        list.setBackground(BG_LIST);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(56);
        list.setFocusable(false);
        list.setBorder(null);

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BG_LIST);

        add(scroll, BorderLayout.CENTER);
    }

    public void addItem(String name, int quantity, BigDecimal unitPrice) {
        model.addElement(new OrderItem(name, quantity, unitPrice));
    }

    public void updateItem(String name, int newQuantity, BigDecimal unitPrice) {
        for (int i = 0; i < model.size(); i++) {
            OrderItem item = model.get(i);

            if (item.name.equals(name)) {
                model.set(i, new OrderItem(name, newQuantity, unitPrice));
                return;
            }
        }

        addItem(name, newQuantity, unitPrice);
    }

    public void removeItem(String name) {
        for (int i = 0; i < model.size(); i++) {
            OrderItem item = model.get(i);

            if (item.name.equals(name)) {
                model.remove(i);
                return;
            }
        }
    }

    public void clearItems() {
        model.clear();
    }

    public int getItemCount() {
        return model.size();
    }

    public static class OrderItem {

        public final String name;
        public final int quantity;
        public final BigDecimal unitPrice;

        public OrderItem(String name, int quantity, BigDecimal unitPrice) {
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public BigDecimal getTotal() {
            return unitPrice.multiply(new BigDecimal(quantity));
        }

    }

}
