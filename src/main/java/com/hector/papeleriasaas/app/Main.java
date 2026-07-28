package com.hector.papeleriasaas.app;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.hector.papeleriasaas.controller.Coordinator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Main {

    static void main(String[] args) {
        try {
            FlatIntelliJLaf.setup();

            UIManager.put("ComboBox.selectionBackground", new Color(0xAEB7BF));
            UIManager.put("ComboBox.selectionForeground", Color.WHITE);

            new Coordinator().startApp();
        } catch (Exception e) {
            System.err.println("Error fatal al inicializar la aplicación: " + e.getMessage());
        }
    }

}
