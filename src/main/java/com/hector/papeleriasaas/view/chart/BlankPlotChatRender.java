package com.hector.papeleriasaas.view.chart;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.List;

/**
 *
 * @author Ing. Hector Acevedo
 */
public interface BlankPlotChatRender {

    String getLabelText(int index);
    void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);
    void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index, List<Path2D.Double> gra);
    boolean mouseMoving(BlankPlotChart chart, MouseEvent evt, Graphics2D g2, SeriesSize size, int index);
    void renderGraphics(Graphics2D g2, List<Path2D.Double> gra);
    int getMaxLegend();

}
