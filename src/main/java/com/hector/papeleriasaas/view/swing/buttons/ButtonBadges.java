package com.hector.papeleriasaas.view.swing.buttons;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ButtonBadges extends JButton {

    private final Animator animator;
    private int targetSize;
    private float animatSize;
    private Point pressedPoint;
    private float alpha;
    private Color effectColor = new Color(173, 173, 173);
    private int badges;

    public ButtonBadges() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                targetSize = Math.max(getWidth(), getHeight()) * 2;
                animatSize = 0;
                pressedPoint = evt.getPoint();
                alpha = 0.5f;

                if (animator.isRunning()) {
                    animator.stop();
                }

                animator.start();
            }
        });

        TimingTargetAdapter target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction;
                }
                animatSize = fraction * targetSize;
                repaint();
            }
        };

        animator = new Animator(400, target);
        animator.setResolution(0);
    }

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
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
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillOval(0, 0, size, size);

        if (pressedPoint != null) {
            g2.setColor(effectColor);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2),
                    (int) animatSize, (int) animatSize);
        }

        g2.dispose();
        g.drawImage(img, x, y, null);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (badges > 0) {
            String value = badges > 9 ? "+9" : badges + "";
            int width = getWidth();
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(value, g2);
            int fw = (int) r2.getWidth();
            int fh = (int) r2.getHeight();
            g2.setColor(getForeground());
            int size = Math.max(fw, fh) + 4;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
            g2.fillOval(width - size, 0, size, size);
            int x = (size - fw) / 2;
            g2.setColor(Color.WHITE);
            g2.setComposite(AlphaComposite.SrcOver);
            g2.drawString(value, width - size + x, ft.getAscent() + 1);
            g2.dispose();
        }
    }

}
