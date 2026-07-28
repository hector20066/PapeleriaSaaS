package com.hector.papeleriasaas.utils;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class NavigatorUtils {

    public static void showPanels(JPanel container, JPanel element) {
        container.removeAll();
        container.add(element, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

}
