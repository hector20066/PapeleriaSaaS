package com.hector.papeleriasaas.view.swing.tables.tablestatus.users;

import com.hector.papeleriasaas.model.enums.Status;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

/**
 *
 * @author hlacevedo
 */
public class TableStatus extends JLabel {
    
    public Status getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private Status type;

    public void setType(Status type) {
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
            
            if (type == Status.INACTIVE) {
                g = new GradientPaint(0, 0, new Color(186, 123, 247), 0, getHeight(), new Color(167, 94, 236));
            } else if (type == Status.ACTIVE) {
                g = new GradientPaint(0, 0, new Color(142, 142, 250), 0, getHeight(), new Color(123, 123, 245));
            }
            
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }

        super.paintComponent(graphics);
    }
    
}
