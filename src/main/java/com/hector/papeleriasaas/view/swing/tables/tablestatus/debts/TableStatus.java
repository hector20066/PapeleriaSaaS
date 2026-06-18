package com.hector.papeleriasaas.view.swing.tables.tablestatus.debts;

import com.hector.papeleriasaas.model.enums.PaymentStatus;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author hlacevedo
 */
public class TableStatus extends JLabel {
    
    public PaymentStatus getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private PaymentStatus type;

    public void setType(PaymentStatus type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g = null;
            
            if (type == PaymentStatus.PENDING) {
                g = new GradientPaint(0, 0, new Color(186, 123, 247), 0, getHeight(), new Color(167, 94, 236));
            } else if (type == PaymentStatus.PAID) {
                g = new GradientPaint(0, 0, new Color(142, 142, 250), 0, getHeight(), new Color(123, 123, 245));
            }
            
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        
        super.paintComponent(graphics);
    }
    
}
