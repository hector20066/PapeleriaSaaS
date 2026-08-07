package com.hector.papeleriasaas.view.swing.tables.tablestatus.debts;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author hlacevedo
 */
public class TableStatus extends JLabel {

    private static final Color DEFAULT_FOREGROUND = Color.WHITE;
    private static final Color SELECTED_FOREGROUND = new Color(15, 89, 140);
    private static final Color DEFAULT_BACKGROUND_START = new Color(186, 123, 247);
    private static final Color DEFAULT_BACKGROUND_END = new Color(167, 94, 236);
    private static final Color SELECTED_BACKGROUND_START = new Color(229, 240, 249);
    private static final Color SELECTED_BACKGROUND_END = new Color(210, 231, 246);
    private boolean selected;
    
    public PaymentStatus getType() {
        return type;
    }

    public TableStatus() {
        setForeground(DEFAULT_FOREGROUND);
    }

    private PaymentStatus type;

    public void setType(PaymentStatus type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setForeground(selected ? SELECTED_FOREGROUND : DEFAULT_FOREGROUND);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g = null;
            
            if (selected) {
                g = new GradientPaint(0, 0, SELECTED_BACKGROUND_START, 0, getHeight(), SELECTED_BACKGROUND_END);
            } else if (type == PaymentStatus.PENDING) {
                g = new GradientPaint(0, 0, DEFAULT_BACKGROUND_START, 0, getHeight(), DEFAULT_BACKGROUND_END);
            } else if (type == PaymentStatus.PAID) {
                g = new GradientPaint(0, 0, new Color(142, 142, 250), 0, getHeight(), new Color(123, 123, 245));
            }
            
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        
        super.paintComponent(graphics);
    }
    
}
