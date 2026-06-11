package com.hector.papeleriasaas.view.swing.buttons;

import com.hector.papeleriasaas.executor.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class WinButton extends javax.swing.JPanel {

    public WinButton() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_salir = new Button();
        btn_maximizar = new Button();
        btn_minimizar = new Button();

        btn_salir.setBackground(new java.awt.Color(240, 61, 61));

        btn_maximizar.setBackground(new java.awt.Color(227, 226, 68));

        btn_minimizar.setBackground(new java.awt.Color(67, 199, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_maximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void initEvent(JFrame frame){
        btn_salir.addActionListener(evt -> {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ReportExecutor.shutdown();
                    ObserverExecutor.shutdown();
                    BackgroundExecutor.shutdown();
                    System.exit(0);
                }
            });

            System.exit(0);
        });

        btn_minimizar.addActionListener(evt -> frame.setState(JFrame.ICONIFIED));

        btn_maximizar.addActionListener(evt -> {
            if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH){
                frame.setExtendedState(JFrame.NORMAL);
            }else{
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Button btn_maximizar;
    private Button btn_minimizar;
    private Button btn_salir;
    // End of variables declaration//GEN-END:variables
}
