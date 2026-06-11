package com.hector.papeleriasaas.view.content.panels;

import com.hector.papeleriasaas.model.dto.DTOCustomer;
import com.hector.papeleriasaas.view.swing.TableHeader;
import com.hector.papeleriasaas.view.swing.buttons.ButtonAction;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class Customers extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();

    public Customers() {
        initComponents();

        String[] titles = new String[] {"Cedula", "Nombre", "Teléfono"};
        model.setColumnIdentifiers(titles);
        jtbl_customers.setModel(model);

        jtbl_customers.setTableHeader(TableHeader.getTableHeader(jtbl_customers));
        
        txt_search.setText("Buscar Cliente");
        txt_search.setForeground(new Color(114, 142, 166));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_customers = new javax.swing.JTable();
        roundedPanel1 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_totalRecords = new javax.swing.JTextField();
        btn_export = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(42, 49, 64));
        jLabel1.setText("Historial de Clientes Registrados en el Sistema");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(42, 49, 64));
        jLabel2.setText("Registro completo de clientes de la papeleria");

        jtbl_customers.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jtbl_customers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbl_customers);

        roundedPanel1.setBackground(new java.awt.Color(242, 242, 242));
        roundedPanel1.setBorderColor(new java.awt.Color(153, 153, 153));

        txt_search.setBackground(new java.awt.Color(242, 242, 242));
        txt_search.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(114, 142, 166));
        txt_search.setText("Buscar proveedor");
        txt_search.setBorder(null);

        btn_search.setBackground(new java.awt.Color(242, 242, 242));
        btn_search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_search.setForeground(new java.awt.Color(42, 49, 64));
        btn_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        btn_search.setBorder(null);
        btn_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txt_search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_search)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 49, 64));
        jLabel3.setText("Total de clientes registrados:");

        txt_totalRecords.setEditable(false);
        txt_totalRecords.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_totalRecords.setForeground(new java.awt.Color(42, 49, 64));

        btn_export.setBackground(new java.awt.Color(65, 90, 119));
        btn_export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        btn_export.setText("  Exportar");
        btn_export.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_export.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(701, 701, 701))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_totalRecords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_totalRecords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void updatePanel(DTOCustomer dto) {
        int id = dto.getId();
        String name = dto.getName();
        String phone = dto.getPhone();

        Object[] data = new Object[]{id, name, phone};
        model.addRow(data);
    }

    public void setControllerSearch(ActionListener action) {
        btn_search.addActionListener(action);
    }
    
    public void setControllerTxt_searchFocusListener(FocusListener listen) {
        this.txt_search.addFocusListener(listen);
    }
    
    public void setControllerTxt_searchDocumentListener(DocumentListener listen) {
        this.txt_search.getDocument().addDocumentListener(listen);
    }
    
    public void setControllerExport(ActionListener action) {
        this.btn_export.addActionListener(action);
    }
    
    public DefaultTableModel getModel() {
        return model;
    }

    public ButtonAction getBtn_export() {
        return btn_export;
    }

    public JButton getBtn_search() {
        return btn_search;
    }

    public JTable getJtbl_customers() {
        return jtbl_customers;
    }

    public JTextField getTxt_search() {
        return txt_search;
    }

    public JTextField getTxt_totalRecords() {
        return txt_totalRecords;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_export;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbl_customers;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_totalRecords;
    // End of variables declaration//GEN-END:variables
}
