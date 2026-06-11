package com.hector.papeleriasaas.view.swing.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class RoundedPanel extends JPanel {

    private final int cornerRadius;
    private boolean drawBorder = true;
    private Color borderColor = Color.BLACK;

    public RoundedPanel() {
        this(15);
    }

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius));

        if (drawBorder) {
            g2.setColor(borderColor != null ? borderColor : getForeground());
            g2.draw(new RoundRectangle2D.Double(0, 0, width - 1, height - 1, cornerRadius, cornerRadius));
        }

        g2.dispose();
    }

}
