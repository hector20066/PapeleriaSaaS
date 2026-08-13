package com.hector.papeleriasaas.view.content.panels.companies;

import com.hector.papeleriasaas.model.dto.product.DTOProductDetails;
import com.hector.papeleriasaas.view.swing.buttons.ButtonAction;
import com.hector.papeleriasaas.view.swing.panels.RoundedPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class PurchaseProductsView extends javax.swing.JPanel {

    DefaultTableModel productList;

    public PurchaseProductsView() {
        initComponents();

        productList = (DefaultTableModel) jtbl_products.getModel();
        configureTableScrollPane();
    }
    
    private void configureTableScrollPane() {
        Color background = new Color(242, 242, 242);
        jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setViewportBorder(BorderFactory.createEmptyBorder());
        jScrollPane2.setBackground(background);
        jScrollPane2.getViewport().setBackground(background);
        jScrollPane2.getViewport().setBorder(null);
        jScrollPane2.setFocusable(false);
        jScrollPane2.getViewport().setFocusable(false);

        scrollBar1.setModel(jScrollPane2.getVerticalScrollBar().getModel());
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlb_title = new javax.swing.JLabel();
        jlb_subtitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_nit = new javax.swing.JTextField();
        txt_rSocial = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        txt_address = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jlb_totalPurchases = new javax.swing.JLabel();
        btn_registerSale = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_cancelPurchase = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jLabel7 = new javax.swing.JLabel();
        btn_showInventory = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_deleteOrder = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        btn_addProduct = new com.hector.papeleriasaas.view.swing.buttons.ButtonAction();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_products = new com.hector.papeleriasaas.view.swing.tables.tabledefault.TableColumn();
        scrollBar1 = new com.hector.papeleriasaas.view.swing.scroll.ScrollBar();
        roundedPanel2 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_product = new javax.swing.JTextField();
        roundedPanel4 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_purchasePrice = new javax.swing.JTextField();
        roundedPanel5 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_amount = new javax.swing.JTextField();
        roundedPanel6 = new com.hector.papeleriasaas.view.swing.panels.RoundedPanel();
        txt_salePrice = new javax.swing.JTextField();
        cbx_productList = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(242, 242, 242));
        jPanel1.setForeground(new java.awt.Color(242, 242, 242));

        jlb_title.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jlb_title.setForeground(new java.awt.Color(42, 49, 64));
        jlb_title.setText("Compra de Productos");

        jlb_subtitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jlb_subtitle.setForeground(new java.awt.Color(42, 49, 64));
        jlb_subtitle.setText("Informacion del producto");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(42, 49, 64));
        jLabel2.setText("Producto:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 49, 64));
        jLabel3.setText("Cantidad:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(42, 49, 64));
        jLabel4.setText("Precio Venta:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(42, 49, 64));
        jLabel5.setText("Productos en el pedido");

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(42, 49, 64));
        jLabel1.setText("Precio Compra:");

        roundedPanel1.setBackground(new java.awt.Color(223, 229, 234));
        roundedPanel1.setBorderColor(java.awt.Color.gray);
        roundedPanel1.setDrawBorder(false);

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(42, 49, 64));
        jLabel6.setText("Proveedor");

        txt_nit.setBackground(new java.awt.Color(223, 229, 234));
        txt_nit.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_nit.setForeground(new java.awt.Color(119, 141, 169));
        txt_nit.setText("Nit");
        txt_nit.setName("txt_nit"); // NOI18N

        txt_rSocial.setBackground(new java.awt.Color(223, 229, 234));
        txt_rSocial.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_rSocial.setForeground(new java.awt.Color(119, 141, 169));
        txt_rSocial.setText("R. Social");
        txt_rSocial.setName("txt_rSocial"); // NOI18N

        txt_phone.setBackground(new java.awt.Color(223, 229, 234));
        txt_phone.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_phone.setForeground(new java.awt.Color(119, 141, 169));
        txt_phone.setText("Telefono");
        txt_phone.setName("txt_phone"); // NOI18N

        txt_address.setBackground(new java.awt.Color(223, 229, 234));
        txt_address.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_address.setForeground(new java.awt.Color(119, 141, 169));
        txt_address.setText("Direccion");
        txt_address.setName("txt_address"); // NOI18N

        txt_email.setBackground(new java.awt.Color(223, 229, 234));
        txt_email.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_email.setForeground(new java.awt.Color(119, 141, 169));
        txt_email.setText("Correo");
        txt_email.setName("txt_email"); // NOI18N

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(42, 49, 64));
        jLabel12.setText("Total a pagar:");

        jlb_totalPurchases.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jlb_totalPurchases.setForeground(new java.awt.Color(42, 49, 64));
        jlb_totalPurchases.setText("0.00");

        btn_registerSale.setBackground(new java.awt.Color(65, 90, 119));
        btn_registerSale.setForeground(new java.awt.Color(255, 255, 255));
        btn_registerSale.setText("Pagar");
        btn_registerSale.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_registerSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_cancelPurchase.setBackground(new java.awt.Color(205, 92, 92));
        btn_cancelPurchase.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelPurchase.setText("Cancelar Compra");
        btn_cancelPurchase.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_cancelPurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(42, 49, 64));
        jLabel7.setText("$");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlb_totalPurchases, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_rSocial)
                            .addComponent(txt_nit)
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addComponent(txt_phone)
                                .addGap(18, 18, 18)
                                .addComponent(txt_address))
                            .addComponent(txt_email))))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_registerSale, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txt_rSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jlb_totalPurchases, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(119, 119, 119)
                .addComponent(btn_registerSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cancelPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        btn_showInventory.setBackground(new java.awt.Color(65, 90, 119));
        btn_showInventory.setForeground(new java.awt.Color(255, 255, 255));
        btn_showInventory.setText("Ver Inventario");
        btn_showInventory.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_showInventory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_deleteOrder.setBackground(new java.awt.Color(205, 92, 92));
        btn_deleteOrder.setForeground(new java.awt.Color(255, 255, 255));
        btn_deleteOrder.setText("Eliminar Producto del Pedido");
        btn_deleteOrder.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_deleteOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_addProduct.setBackground(new java.awt.Color(65, 90, 119));
        btn_addProduct.setText("Agregar Producto al Pedido");
        btn_addProduct.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_addProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jtbl_products.setBackground(new java.awt.Color(242, 242, 242));
        jtbl_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio Compra", "Precio Venta", "Total a pagar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtbl_products);

        scrollBar1.setBackground(new java.awt.Color(242, 242, 242));

        roundedPanel2.setBackground(new java.awt.Color(242, 242, 242));

        txt_product.setBackground(new java.awt.Color(242, 242, 242));
        txt_product.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_product.setBorder(null);

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_product)
                .addContainerGap())
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPanel4.setBackground(new java.awt.Color(242, 242, 242));

        txt_purchasePrice.setBackground(new java.awt.Color(242, 242, 242));
        txt_purchasePrice.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_purchasePrice.setBorder(null);

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_purchasePrice)
                .addContainerGap())
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_purchasePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedPanel5.setBackground(new java.awt.Color(242, 242, 242));

        txt_amount.setBackground(new java.awt.Color(242, 242, 242));
        txt_amount.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_amount.setBorder(null);

        javax.swing.GroupLayout roundedPanel5Layout = new javax.swing.GroupLayout(roundedPanel5);
        roundedPanel5.setLayout(roundedPanel5Layout);
        roundedPanel5Layout.setHorizontalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_amount, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundedPanel5Layout.setVerticalGroup(
            roundedPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        roundedPanel6.setBackground(new java.awt.Color(242, 242, 242));

        txt_salePrice.setBackground(new java.awt.Color(242, 242, 242));
        txt_salePrice.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_salePrice.setBorder(null);

        javax.swing.GroupLayout roundedPanel6Layout = new javax.swing.GroupLayout(roundedPanel6);
        roundedPanel6.setLayout(roundedPanel6Layout);
        roundedPanel6Layout.setHorizontalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_salePrice)
                .addContainerGap())
        );
        roundedPanel6Layout.setVerticalGroup(
            roundedPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_salePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cbx_productList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setBackground(new java.awt.Color(42, 49, 64));
        jLabel8.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(42, 49, 64));
        jLabel8.setText("Producto Existente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(roundedPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(roundedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(roundedPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(59, 59, 59)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbx_productList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(roundedPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlb_title)
                                    .addComponent(jlb_subtitle)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_deleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_showInventory, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_showInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlb_title))
                .addGap(21, 21, 21)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jlb_subtitle)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_productList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundedPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roundedPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundedPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(scrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_deleteOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
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

    public void loadProductList(DTOProductDetails dtoProduct) {
        cbx_productList.addItem(dtoProduct.getDescription());
    }
    
    public void loadProduct(DTOProductDetails dtoProduct) {
        txt_product.setText(dtoProduct.getDescription());
        txt_salePrice.setText(String.valueOf(dtoProduct.getSalePrice()));
        txt_purchasePrice.setText(String.valueOf(dtoProduct.getPurchasePrice()));
    }
    
    public void setControllerRegister(ActionListener action) {
        btn_registerSale.addActionListener(action);
    }

    public void setControllerSeeInventory(ActionListener action) {
        btn_showInventory.addActionListener(action);
    }

    public void setControllerAddProduct(ActionListener action) {
        btn_addProduct.addActionListener(action);
    }

    public void setControllerDeleteOrder(ActionListener action) {
        btn_deleteOrder.addActionListener(action);
    }

    public void setControllerCancelPurchase(ActionListener action) {
        btn_cancelPurchase.addActionListener(action);
    }

    public void setControllerTxt_rSocialFocus(FocusListener listen) {
        txt_rSocial.addFocusListener(listen);
    }

    public void setControllerTxt_supplierNitFocus(FocusListener listen) {
        txt_nit.addFocusListener(listen);
    }

    public void setControllerTxt_phoneFocus(FocusListener listen) {
        txt_phone.addFocusListener(listen);
    }

    public void setControllerTxt_addressFocus(FocusListener listen) {
        txt_address.addFocusListener(listen);
    }

    public void setControllerTxt_emailFocus(FocusListener listen) {
        txt_email.addFocusListener(listen);
    }
    
    public void setControllerSelectProduct(ActionListener action) {
        cbx_productList.addActionListener(action);
    }

    public DefaultTableModel getProductList() {
        return productList;
    }

    public ButtonAction getBtn_addProduct() {
        return btn_addProduct;
    }

    public ButtonAction getBtn_cancelPurchase() {
        return btn_cancelPurchase;
    }

    public ButtonAction getBtn_deleteOrder() {
        return btn_deleteOrder;
    }

    public ButtonAction getBtn_registerSale() {
        return btn_registerSale;
    }

    public ButtonAction getBtn_showInventory() {
        return btn_showInventory;
    }

    public JLabel getJlb_title() {
        return jlb_title;
    }

    public JLabel getJlb_totalPurchases() {
        return jlb_totalPurchases;
    }

    public JTable getJtbl_products() {
        return jtbl_products;
    }

    public RoundedPanel getRoundedPanel1() {
        return roundedPanel1;
    }

    public JTextField getTxt_address() {
        return txt_address;
    }

    public JTextField getTxt_amount() {
        return txt_amount;
    }

    public JTextField getTxt_email() {
        return txt_email;
    }

    public JTextField getTxt_nit() {
        return txt_nit;
    }

    public JTextField getTxt_phone() {
        return txt_phone;
    }

    public JTextField getTxt_product() {
        return txt_product;
    }

    public JTextField getTxt_purchasePrice() {
        return txt_purchasePrice;
    }

    public JTextField getTxt_rSocial() {
        return txt_rSocial;
    }

    public JTextField getTxt_salePrice() {
        return txt_salePrice;
    }

    public JComboBox<String> getCbx_productList() {
        return cbx_productList;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_addProduct;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_cancelPurchase;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_deleteOrder;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_registerSale;
    private com.hector.papeleriasaas.view.swing.buttons.ButtonAction btn_showInventory;
    private javax.swing.JComboBox<String> cbx_productList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlb_subtitle;
    private javax.swing.JLabel jlb_title;
    private javax.swing.JLabel jlb_totalPurchases;
    private com.hector.papeleriasaas.view.swing.tables.tabledefault.TableColumn jtbl_products;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel1;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel2;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel4;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel5;
    private com.hector.papeleriasaas.view.swing.panels.RoundedPanel roundedPanel6;
    private com.hector.papeleriasaas.view.swing.scroll.ScrollBar scrollBar1;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nit;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_product;
    private javax.swing.JTextField txt_purchasePrice;
    private javax.swing.JTextField txt_rSocial;
    private javax.swing.JTextField txt_salePrice;
    // End of variables declaration//GEN-END:variables
}
