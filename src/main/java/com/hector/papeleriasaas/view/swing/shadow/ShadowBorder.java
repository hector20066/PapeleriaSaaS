package com.hector.papeleriasaas.view.swing.shadow;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Singleton for creating borders with a shadow effect.
 *
 * <p>
 * Generates inner ({@code createShadowIn}) and outer ({@code createShadowOut})
 * shadow images using {@link ShadowRenderer}. Defines the enum {@code ShadowType}
 * to distinguish between the two types.
 * </p>
 *
 * @author Ing. Hector Acevedo
 * @see ShadowRenderer
 * @since 1.0
 */
public class ShadowBorder {

    private static ShadowBorder instance;

    /**
     * Private builder to avoid external instantiation.
     */
    private ShadowBorder() {

    }

    /**
     * Gets the unique instance of {@code ShadowBorder}.
     *
     * @return singleton instance
     */
    public static ShadowBorder getInstance() {
        if (instance == null) {
            instance = new ShadowBorder();
        }

        return instance;
    }

    /**
     * Create an image with an inner shadow based on the component's dimensions.
     *
     * @param com    reference component
     * @param border shadow border size
     * @param radius corner radius
     * @return image with inner shadow
     */
    public BufferedImage createShadowIn(Component com, int border, int radius) {
        return createShadowIn(com.getWidth(), com.getHeight(), border, radius, com.getBackground());
    }

    /**
     * Divide the area into a triangular shape to separate the shaded areas.
     *
     * @param width  area width
     * @param height height area
     * @return triangular shape
     */
    private Shape split(int width, int height) {
        Path2D.Float p = new Path2D.Float();
        p.moveTo(width, 0);
        p.lineTo(width, height);
        p.lineTo(0, height);

        return p;
    }

    /**
     * Create an image with an outer shadow based on the component's dimensions.
     *
     * @param com    reference component
     * @param border shadow border size
     * @param radius corner radius
     * @return image with outer shadow
     */
    public BufferedImage createShadowOut(Component com, int border, int radius) {
        return createShadowOut(com.getWidth(), com.getHeight(), border, radius, com.getBackground());
    }

    /**
     * Create an image with an inner shadow with specific dimensions and color.
     *
     * @param width  image width
     * @param height height of the image
     * @param border border size
     * @param radius corner radius
     * @param color  background color
     * @return image with inner shadow
     */
    public BufferedImage createShadowIn(int width, int height, int border, int radius, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = border;
        int y = border;
        int size = radius;

        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, border, border);

        Shape s = new RoundRectangle2D.Double(x, y, width - border * 2, height - border * 2, size, size);
        Area area = new Area(new Rectangle(0, 0, width - border, height - border));
        area.subtract(new Area(s));
        area.subtract(new Area(split(width, height)));

        g2.drawImage(new ShadowRenderer(border, 0.4f, new Color(89, 69, 137)).createShadow(createImage(width, height, area)), -border, -border, null);

        Shape s_w = new RoundRectangle2D.Double(x, y, width - border * 2, height - border * 2, size, size);
        Area area_w = new Area(new Rectangle(0, 0, width, height));
        area_w.subtract(new Area(s_w));
        area_w.intersect(new Area(split(width, height)));

        g2.drawImage(new ShadowRenderer(border, 1f, new Color(255, 255, 255)).createShadow(createImage(width, height, area_w)), -border, -border, null);
        g2.fill(area);
        g2.fill(area_w);
        g2.setComposite(AlphaComposite.Clear);

        Shape cut = new RoundRectangle2D.Double(x, y, width - border * 2, height - border * 2, size, size);
        Area bg = new Area(new Rectangle(0, 0, width, height));
        bg.subtract(new Area(cut));

        g2.fill(bg);
        g2.dispose();

        return img;
    }

    /**
     * Create an image with an outer shadow with specific dimensions and color.
     *
     * @param width  image width
     * @param height height of the image
     * @param border border size
     * @param radius corner radius
     * @param color  background color
     * @return image with outer shadow
     */
    public BufferedImage createShadowOut(int width, int height, int border, int radius, Color color) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 0;
        int y = 0;
        int size = radius;

        g2.drawImage(new ShadowRenderer(border, 1f, new Color(255, 255, 255)).createShadow(createImage(x, y, width - border * 2, height - border * 2, size)), 0, 0, null);
        g2.drawImage(new ShadowRenderer(border, 0.4f, new Color(89, 69, 137)).createShadow(createImage(x, y, width - border * 3, height - border * 3, size)), border, border, null);
        g2.setColor(color);
        g2.fillRoundRect(x + border, y + border, width - border * 2, height - border * 2, size, size);
        g2.dispose();

        return img;
    }

    /**
     * Create an auxiliary image from a cropped area.
     *
     * @param width  image width
     * @param height height of the image
     * @param area   area to be filled
     * @return image with the area filled
     */
    private BufferedImage createImage(int width, int height, Area area) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(area);

        return img;
    }

    /**
     * Create an auxiliary image with a rounded rectangle.
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  width of the rectangle
     * @param height height of the rectangle
     * @param size   corner radius
     * @return image with filled rectangle
     */
    private BufferedImage createImage(int x, int y, int width, int height, int size) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(x, y, width, height, size, size);

        return img;
    }

    /**
     * List that defines the types of shadow.
     */
    public enum ShadowType {

        IN_SHADOW,
        OUT_SHADOW

    }

}
