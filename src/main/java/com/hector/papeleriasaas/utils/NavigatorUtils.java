package com.hector.papeleriasaas.utils;

import com.hector.papeleriasaas.view.content.ContentView;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class NavigatorUtils {

    public static void showPanels(JPanel panel) {
        ContentView.getJpl_content().removeAll();
        ContentView.getJpl_content().add(panel, BorderLayout.CENTER);
        ContentView.getJpl_content().revalidate();
        ContentView.getJpl_content().repaint();
    }

}
