package com.hector.papeleriasaas.view.content.panels.companies;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.hector.papeleriasaas.controller.ProductPanelListener;
import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.view.swing.buttons.Button;
import com.hector.papeleriasaas.view.swing.buttons.ButtonAction;
import com.hector.papeleriasaas.view.swing.lists.OrderItemList;
import com.hector.papeleriasaas.view.swing.panels.RoundedPanel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class SalesManagement extends javax.swing.JPanel {

    public SalesManagement() {
        initComponents();

        jpl_content.setLayout(new BoxLayout(jpl_content, BoxLayout.Y_AXIS));
        scrollProducts.getVerticalScrollBar().setUnitIncrement(15);

        txt_search.setText("Buscar Producto");
        txt_search.setForeground(new Color(114, 142, 166));

        jpl_school.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpl_art.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jpl_office.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        roundedPanel2 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_customerName = new javax.swing.JTextField();
        txt_customerId = new javax.swing.JTextField();
        txt_customerPhone = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        orderItemList = new com.hector.papeleriasaas.view.swing.lists.OrderItemList();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jlb_totalSale = new javax.swing.JLabel();
        btn_sale = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_cancelSale = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jLabel12 = new javax.swing.JLabel();
        txt_amountPaid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        scrollProducts = new javax.swing.JScrollPane();
        jpl_content = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpl_school = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jpl_office = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpl_art = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(42, 49, 64));
        jLabel1.setText("Ventas");

        roundedPanel1.setBackground(new java.awt.Color(242, 242, 242));
        roundedPanel1.setBorderColor(new java.awt.Color(153, 153, 153));

        txt_search.setBackground(new java.awt.Color(242, 242, 242));
        txt_search.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(114, 142, 166));
        txt_search.setText("Buscar producto");
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
                .addComponent(btn_search)
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_search)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedPanel2.setBackground(new java.awt.Color(223, 229, 234));
        roundedPanel2.setBorderColor(null);
        roundedPanel2.setDrawBorder(false);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 49, 64));
        jLabel3.setText("Detalle de la venta");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(42, 49, 64));
        jLabel4.setText("Cliente");

        txt_customerName.setBackground(new java.awt.Color(223, 229, 234));
        txt_customerName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_customerName.setForeground(new java.awt.Color(119, 141, 169));
        txt_customerName.setText("Nombre");

        txt_customerId.setBackground(new java.awt.Color(223, 229, 234));
        txt_customerId.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_customerId.setForeground(new java.awt.Color(119, 141, 169));
        txt_customerId.setText("Cedula");

        txt_customerPhone.setBackground(new java.awt.Color(223, 229, 234));
        txt_customerPhone.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_customerPhone.setForeground(new java.awt.Color(119, 141, 169));
        txt_customerPhone.setText("Telefono");

        orderItemList.setBackground(new java.awt.Color(242, 242, 242));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(42, 49, 64));
        jLabel5.setText("Total a Pagar:");

        jlb_totalSale.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jlb_totalSale.setForeground(new java.awt.Color(42, 49, 64));
        jlb_totalSale.setText("0.00");

        btn_sale.setBackground(new java.awt.Color(65, 90, 119));
        btn_sale.setForeground(new java.awt.Color(255, 255, 255));
        btn_sale.setText("Pagar");
        btn_sale.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_sale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_cancelSale.setBackground(new java.awt.Color(205, 92, 92));
        btn_cancelSale.setText("Cancelar Venta");
        btn_cancelSale.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_cancelSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(42, 49, 64));
        jLabel12.setText("Cant. Pagada:");

        txt_amountPaid.setBackground(new java.awt.Color(223, 229, 234));
        txt_amountPaid.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        txt_amountPaid.setForeground(new java.awt.Color(42, 49, 64));
        txt_amountPaid.setText("0.00");

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(42, 49, 64));
        jLabel13.setText("$");

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(42, 49, 64));
        jLabel14.setText("$");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_customerName, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                                .addComponent(txt_customerId, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txt_customerPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                                .addGap(4, 4, 4)))))))
                        .addGap(22, 22, 22))
                    .addGroup(roundedPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(orderItemList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundedPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlb_totalSale, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(txt_amountPaid))
                                .addGap(15, 15, 15)))))
                .addContainerGap())
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_sale, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_customerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_customerPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlb_totalSale, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_amountPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(24, 24, 24)
                .addComponent(btn_sale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        scrollProducts.setBackground(new java.awt.Color(242, 242, 242));
        scrollProducts.setBorder(null);

        jpl_content.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout jpl_contentLayout = new javax.swing.GroupLayout(jpl_content);
        jpl_content.setLayout(jpl_contentLayout);
        jpl_contentLayout.setHorizontalGroup(
            jpl_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        jpl_contentLayout.setVerticalGroup(
            jpl_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
        );

        scrollProducts.setViewportView(jpl_content);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(42, 49, 64));
        jLabel2.setText("Productos Disponibles");

        jpl_school.setBackground(new java.awt.Color(242, 242, 242));
        jpl_school.setBorderColor(new java.awt.Color(96, 150, 186));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/school.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(42, 49, 64));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Escolar");

        javax.swing.GroupLayout jpl_schoolLayout = new javax.swing.GroupLayout(jpl_school);
        jpl_school.setLayout(jpl_schoolLayout);
        jpl_schoolLayout.setHorizontalGroup(
            jpl_schoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_schoolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpl_schoolLayout.setVerticalGroup(
            jpl_schoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_schoolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_schoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpl_schoolLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_office.setBackground(new java.awt.Color(242, 242, 242));
        jpl_office.setBorderColor(new java.awt.Color(96, 150, 186));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/lamp-desk.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(42, 49, 64));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Oficina");

        javax.swing.GroupLayout jpl_officeLayout = new javax.swing.GroupLayout(jpl_office);
        jpl_office.setLayout(jpl_officeLayout);
        jpl_officeLayout.setHorizontalGroup(
            jpl_officeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_officeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpl_officeLayout.setVerticalGroup(
            jpl_officeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_officeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_officeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpl_officeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpl_art.setBackground(new java.awt.Color(242, 242, 242));
        jpl_art.setBorderColor(new java.awt.Color(96, 150, 186));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/brush.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(42, 49, 64));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Arte");

        javax.swing.GroupLayout jpl_artLayout = new javax.swing.GroupLayout(jpl_art);
        jpl_art.setLayout(jpl_artLayout);
        jpl_artLayout.setHorizontalGroup(
            jpl_artLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_artLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpl_artLayout.setVerticalGroup(
            jpl_artLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl_artLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpl_artLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpl_artLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollProducts)
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jpl_school, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpl_office, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpl_art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(86, 86, 86)))
                .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpl_school, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpl_office, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpl_art, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(scrollProducts)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
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

    public void clearPanel() {
        jpl_content.removeAll();
        jpl_content.revalidate();
        jpl_content.repaint();
    }

    public void addProductPanel(DTOProductDetails product, ProductPanelListener listener) {
        RoundedPanel panelProduct = new RoundedPanel(15);
        panelProduct.setLayout(new BorderLayout(0, 0));
        panelProduct.setBackground(new Color(248, 249, 251));
        panelProduct.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        panelProduct.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        panelProduct.setDrawBorder(false);

        JLabel lblImage = new JLabel();
        lblImage.setPreferredSize(new Dimension(52, 52));
        lblImage.setMinimumSize(new Dimension(52, 52));
        lblImage.setMaximumSize(new Dimension(52, 52));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        lblImage.setOpaque(false);
        lblImage.setIcon(new FlatSVGIcon("image/box.svg"));

        JPanel imageWrapper = new JPanel(new GridBagLayout());
        imageWrapper.setOpaque(false);
        imageWrapper.setPreferredSize(new Dimension(74, 105));
        imageWrapper.add(lblImage);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

        JLabel lblName = new JLabel(product.getDescription());
        lblName.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblName.setForeground(new Color(25, 35, 55));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);

        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.of("es", "CO"));
        String priceFormatted = "$" + currencyFormat.format(product.getSalePrice());
        JLabel lblPrice = new JLabel(priceFormatted);
        lblPrice.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblPrice.setForeground(new Color(70, 130, 80));
        lblPrice.setAlignmentX(Component.LEFT_ALIGNMENT);

        int stock = product.getAmount();
        JLabel lblStock = new JLabel("Stock: " + stock);
        lblStock.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStock.setForeground(stock <= 3 ? new Color(200, 60, 60) : new Color(130, 135, 145));
        lblStock.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        quantityPanel.setOpaque(false);
        quantityPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblQuantity = new JLabel("0");
        lblQuantity.setFont(new Font("SansSerif", Font.BOLD, 17));
        lblQuantity.setForeground(new Color(25, 35, 55));
        lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantity.setPreferredSize(new Dimension(32, 32));

        Button btnDecrease = new Button();
        btnDecrease.setIcon(new FlatSVGIcon("image/minus (1).svg"));
        btnDecrease.setPreferredSize(new Dimension(32, 32));
        btnDecrease.setBackground(new Color(220, 220, 225));
        btnDecrease.setFocusable(false);
        btnDecrease.addActionListener(e -> listener.onDecreaseQuantity(product, lblQuantity));

        Button btnIncrease = new Button();
        btnIncrease.setIcon(new FlatSVGIcon("image/plus (2).svg"));
        btnIncrease.setPreferredSize(new Dimension(32, 32));
        btnIncrease.setBackground(new Color(180, 210, 240));
        btnIncrease.setFocusable(false);
        btnIncrease.addActionListener(e -> listener.onIncreaseQuantity(product, lblQuantity));

        quantityPanel.add(btnDecrease);
        quantityPanel.add(lblQuantity);
        quantityPanel.add(btnIncrease);

        centerPanel.add(lblName);
        centerPanel.add(Box.createVerticalStrut(2));
        centerPanel.add(lblPrice);
        centerPanel.add(Box.createVerticalStrut(3));
        centerPanel.add(lblStock);
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(quantityPanel);

        ButtonAction btnAdd = new ButtonAction();
        btnAdd.setIcon(new FlatSVGIcon("image/plus.svg"));
        btnAdd.setBackground(new Color(100, 150, 210));
        btnAdd.setHorizontalAlignment(SwingConstants.CENTER);
        btnAdd.setVerticalAlignment(SwingConstants.CENTER);
        btnAdd.setFocusable(false);
        btnAdd.setPreferredSize(new Dimension(44, 44));
        btnAdd.addActionListener(e -> {
            int current = Integer.parseInt(lblQuantity.getText());
            listener.onAddToOrder(product, current);
        });

        JPanel rightWrapper = new JPanel(new GridBagLayout());
        rightWrapper.setOpaque(false);
        rightWrapper.setPreferredSize(new Dimension(56, 105));
        rightWrapper.add(btnAdd);

        panelProduct.add(imageWrapper, BorderLayout.WEST);
        panelProduct.add(centerPanel, BorderLayout.CENTER);
        panelProduct.add(rightWrapper, BorderLayout.EAST);

        jpl_content.add(panelProduct);
        jpl_content.add(Box.createVerticalStrut(8));
        jpl_content.revalidate();
        jpl_content.repaint();
    }

    public void setControllerSale(ActionListener action) {
        btn_sale.addActionListener(action);
    }

    public void setControllerCancelSale(ActionListener action) {
        btn_cancelSale.addActionListener(action);
    }

    public void setControllerSearch(ActionListener action) {
        btn_search.addActionListener(action);
    }

    public void setControllerJpl_schoolMouse(MouseListener listen) {
        jpl_school.addMouseListener(listen);
    }

    public void setControllerJpl_artMouse(MouseListener listen) {
        jpl_art.addMouseListener(listen);
    }

    public void setControllerJpl_office(MouseListener listen) {
        jpl_office.addMouseListener(listen);
    }

    public void setControllerTxt_searchFocus(FocusListener listen) {
        txt_search.addFocusListener(listen);
    }

    public void setControllerTxt_searchDocument(DocumentListener listen) {
        txt_search.getDocument().addDocumentListener(listen);
    }

    public void setControllerTxt_customerNameFocus(FocusListener listen) {
        txt_customerName.addFocusListener(listen);
    }

    public void setControllerTxt_customerPhoneFocus(FocusListener listen) {
        txt_customerPhone.addFocusListener(listen);
    }

    public void setControllerTxt_customerIdFocus(FocusListener listen) {
        txt_customerId.addFocusListener(listen);
    }
    
    public void setControllerTxt_amountPaidFocus(FocusListener listen) {
        txt_amountPaid.addFocusListener(listen);
    }

    public JButton getBtn_search() {
        return btn_search;
    }

    public ButtonAction getBtn_cancelSale() {
        return btn_cancelSale;
    }

    public ButtonAction getBtn_sale() {
        return btn_sale;
    }

    public JLabel getJlb_totalSale() {
        return jlb_totalSale;
    }

    public RoundedPanel getJpl_art() {
        return jpl_art;
    }

    public JPanel getJpl_content() {
        return jpl_content;
    }

    public RoundedPanel getJpl_office() {
        return jpl_office;
    }

    public RoundedPanel getJpl_school() {
        return jpl_school;
    }

    public OrderItemList getOrderItemList() {
        return orderItemList;
    }

    public JScrollPane getScrollProducts() {
        return scrollProducts;
    }

    public JTextField getTxt_amountPaid() {
        return txt_amountPaid;
    }

    public JTextField getTxt_customerId() {
        return txt_customerId;
    }

    public JTextField getTxt_customerName() {
        return txt_customerName;
    }

    public JTextField getTxt_customerPhone() {
        return txt_customerPhone;
    }

    public JTextField getTxt_search() {
        return txt_search;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_cancelSale;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_sale;
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jlb_totalSale;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel jpl_art;
    private javax.swing.JPanel jpl_content;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel jpl_office;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel jpl_school;
    private com.hector.papeleriasaas.view.swing.lists.OrderItemList orderItemList;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel1;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel2;
    private javax.swing.JScrollPane scrollProducts;
    private javax.swing.JTextField txt_amountPaid;
    private javax.swing.JTextField txt_customerId;
    private javax.swing.JTextField txt_customerName;
    private javax.swing.JTextField txt_customerPhone;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
