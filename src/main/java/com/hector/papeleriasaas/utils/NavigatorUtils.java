package com.hector.papeleriasaas.utils;

import com.hector.papeleriasaas.view.content.Content;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class NavigatorUtils {

    public static void showPanels(JPanel panel) {
        Content.getJpl_content().removeAll();
        Content.getJpl_content().add(panel, BorderLayout.CENTER);
        Content.getJpl_content().revalidate();
        Content.getJpl_content().repaint();
    }

}
