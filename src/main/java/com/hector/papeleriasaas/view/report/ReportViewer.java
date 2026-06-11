package com.hector.papeleriasaas.view.report;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;

/**
 *
 * @author Ing. Hector Acevedo
 */
public final class ReportViewer {

    private ReportViewer() {

    }

    public static void show(JasperPrint jasperPrint) {
        SwingUtilities.invokeLater(() -> {
            JasperViewer viewer = new JasperViewer(jasperPrint, false);

            viewer.setLocationRelativeTo(null);
            viewer.setResizable(false);
            viewer.setVisible(true);
        });
    }

}
