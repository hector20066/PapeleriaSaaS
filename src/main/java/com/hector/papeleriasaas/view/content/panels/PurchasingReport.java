package com.hector.papeleriasaas.view.content.panels;

import com.hector.papeleriasaas.model.dto.purchase.DTOPurchaseDetails;
import com.hector.papeleriasaas.view.swing.TableHeader;
import com.hector.papeleriasaas.view.swing.buttons.ButtonAction;
import com.hector.papeleriasaas.view.swing.panels.RoundedPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchasingReport extends javax.swing.JPanel {

    DefaultTableModel purchaseList = new DefaultTableModel();

    public PurchasingReport() {
        initComponents();
        
        String[] titles = new String[]{"Num. Compra", "Producto", "ID producto", "Cant. Comprada", "Precio compra",
                "Precio Venta", "Fecha", "Proveedor", "NIT proveedor", "Total Compra", "Ganancias"};
        purchaseList.setColumnIdentifiers(titles);
        jtbl_purchases.setModel(purchaseList);
       
        jtbl_purchases.setTableHeader(TableHeader.getTableHeader(jtbl_purchases));

        txt_search.setText("Buscar Compra");
        txt_search.setForeground(new Color(114, 142, 166));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbl_purchases = new javax.swing.JTable();
        roundedPanel1 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_export = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(42, 49, 64));
        jLabel1.setText("Registro Total de Compras");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(42, 49, 64));
        jLabel2.setText("Registro General de Compras");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 1, 2, new java.awt.Color(114, 142, 166)));

        jtbl_purchases.setModel(new javax.swing.table.DefaultTableModel(
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
        jtbl_purchases.setSelectionBackground(new java.awt.Color(174, 183, 191));
        jScrollPane1.setViewportView(jtbl_purchases);

        roundedPanel1.setBackground(new java.awt.Color(242, 242, 242));
        roundedPanel1.setBorderColor(new java.awt.Color(153, 153, 153));

        txt_search.setBackground(new java.awt.Color(242, 242, 242));
        txt_search.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(114, 142, 166));
        txt_search.setText("Buscar Compra");
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
                .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btn_export.setBackground(new java.awt.Color(74, 124, 111));
        btn_export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        btn_export.setText("  Exportar");
        btn_export.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_export.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1091, Short.MAX_VALUE)
                            .addComponent(jLabel2))))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_export, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
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

    public void updatePanel(DTOPurchaseDetails dto){
        int numPurchase = dto.getNumPurchase();
        String productName = dto.getProductDescription();
        int productId = dto.getProductId();
        int quantityPurchased = dto.getQuantityPurchased();
        BigDecimal purchasePrice = dto.getPurchasePrice();
        BigDecimal salePrice = dto.getSalePrice();
        LocalDate date = dto.getDate();
        String supplierName = dto.getSupplier();
        int supplierNit = dto.getSupplierNit();
        BigDecimal totalPurchase = dto.getTotalPaid();
        BigDecimal earnings = dto.getEarnings();

        Object[] data = new Object[]{numPurchase, productName, productId, quantityPurchased, purchasePrice, salePrice,
                date, supplierName, supplierNit, totalPurchase, earnings};
        purchaseList.addRow(data);
    }
    
    public void setControllerSearch(ActionListener action){
        btn_search.addActionListener(action);
    }
    
    public void setControllerExport(ActionListener action) {
        btn_export.addActionListener(action);
    }
    
    public void setControllerTxt_searchDocument(DocumentListener listen){
        txt_search.getDocument().addDocumentListener(listen);
    }
    
    public void setControllerTxt_searchFocus(FocusListener listen){
        txt_search.addFocusListener(listen);
    }

    public DefaultTableModel getPurchaseList() {
        return purchaseList;
    }

    public ButtonAction getBtn_export() {
        return btn_export;
    }

    public JButton getBtn_search() {
        return btn_search;
    }

    public JTable getJtbl_purchases() {
        return jtbl_purchases;
    }

    public RoundedPanel getRoundedPanel1() {
        return roundedPanel1;
    }

    public JTextField getTxt_search() {
        return txt_search;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_export;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbl_purchases;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel1;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
