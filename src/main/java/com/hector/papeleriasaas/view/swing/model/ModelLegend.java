package com.hector.papeleriasaas.view.swing.model;

import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class ModelLegend {

    private String name;
    private Color color;
    private Color colorLight;

    public ModelLegend(String name, Color color, Color colorLight) {
        this.name = name;
        this.color = color;
        this.colorLight = colorLight;
    }

    public ModelLegend() {

    }

    public void setNombre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorLight() {
        return colorLight;
    }

    public void setColorLight(Color colorLight) {
        this.colorLight = colorLight;
    }

}
