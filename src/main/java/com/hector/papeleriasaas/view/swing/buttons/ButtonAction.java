package com.hector.papeleriasaas.view.swing.buttons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ButtonAction extends JButton {

    private Timer timer;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private Color effectColor = new Color(173, 173, 173);

    public ButtonAction() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 10, 8, 10));
        setHorizontalAlignment(JButton.LEFT);
        setBackground(new Color(40, 90, 165));
        setForeground(new Color(250, 250, 250));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        initAnimator();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = evt.getPoint();
                alpha = 0.5f;

                if (timer.isRunning()) {
                    timer.stop();
                }

                timer.start();
            }
        });
    }

    private void initAnimator() {
        int delay = 16;
        int duration = 400;
        int steps = duration / delay;

        timer = new Timer(delay, e -> {
            animatSize = Math.min(animatSize + (float) targetSize / steps, targetSize);
            float fraction = animatSize / targetSize;

            if (fraction > 0.5f) {
                alpha = 1 - fraction;
            }

            if (animatSize >= targetSize) {
                timer.stop();
            }

            repaint();
        });
    }

    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 10, 10);

        if (pressedPoint != null) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, 10, 10));
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, clampAlpha(alpha)));

            area.intersect(new Area(new Ellipse2D.Double(
                    pressedPoint.x - animatSize / 2,
                    pressedPoint.y - animatSize / 2,
                    animatSize,
                    animatSize
            )));

            g2.fill(area);
        }

        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        if (isSelected()) {
            int width = getWidth();
            int height = getHeight();

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(25, 25, 25));
            g2.fillRoundRect(0, 0, width - 1, height - 1, 10, 10);
            g2.dispose();
        }

        super.paint(g);
    }

    private float clampAlpha(float value) {
        if (value < 0f) return 0f;
        return Math.min(value, 1f);
    }

}
