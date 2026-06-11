package com.hector.papeleriasaas.view.swing.lists;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ItemCellRenderer implements ListCellRenderer<OrderItemList.OrderItem> {

    private static final Color BG_ITEM = new Color(255, 255, 255);
    private static final Color BG_ITEM_ALT = new Color(248, 250, 252);
    private static final Color COLOR_QTY = new Color(100, 116, 139);
    private static final Color COLOR_NAME = new Color(30, 41, 59);
    private static final Color COLOR_PRICE = new Color(15, 118, 110);
    private static final Color COLOR_SEP = new Color(241, 245, 249);

    private static final Font FONT_QTY = new Font("SansSerif", Font.BOLD, 13);
    private static final Font FONT_NAME = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font FONT_PRICE = new Font("SansSerif", Font.BOLD, 13);

    @Override
    public Component getListCellRendererComponent(JList<? extends OrderItemList.OrderItem> list, OrderItemList.OrderItem value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        OrderItemList.OrderItem item = value;

        JPanel row = new JPanel(new GridBagLayout());
        row.setOpaque(true);
        row.setBackground(isSelected ? new Color(240, 253, 250) : (index % 2 == 0 ? BG_ITEM : BG_ITEM_ALT));

        row.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_SEP),
                new EmptyBorder(0, 14, 0, 14)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel qtyLabel = new JLabel(item.quantity + "x");
        qtyLabel.setFont(FONT_QTY);
        qtyLabel.setForeground(COLOR_QTY);
        qtyLabel.setHorizontalAlignment(SwingConstants.LEFT);
        qtyLabel.setPreferredSize(new Dimension(40, 56));
        qtyLabel.setMinimumSize(new Dimension(40, 56));
        qtyLabel.setMaximumSize(new Dimension(40, 56));
        gbc.gridx = 0;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 10);
        row.add(qtyLabel, gbc);

        JLabel nameLabel = new JLabel(item.name);
        nameLabel.setFont(FONT_NAME);
        nameLabel.setForeground(COLOR_NAME);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 0, 10);
        row.add(nameLabel, gbc);

        String priceStr = "$" + String.format("%,.0f", item.getTotal());
        JLabel priceLabel = new JLabel(priceStr);
        priceLabel.setFont(FONT_PRICE);
        priceLabel.setForeground(COLOR_PRICE);
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        priceLabel.setPreferredSize(new Dimension(90, 56));
        priceLabel.setMinimumSize(new Dimension(90, 56));
        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        row.add(priceLabel, gbc);

        return row;
    }

}
